import axios from "axios";
import { useUserStore } from '@/stores/user'
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const baseURL = "http://localhost:9999";

// 创建axios实例（单例模式）
const instance = axios.create({
    baseURL,
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json'
    }
});

// 请求拦截器
instance.interceptors.request.use(
    (config) => {
        const userStore = useUserStore()
        if (userStore.token) {
            config.headers.Authorization = `${userStore.token}`
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

// 响应拦截器
instance.interceptors.response.use(
    (response) => {
        const res = response.data
        if (res.code === 0) {
            return res.data || res // 根据您的接口规范调整
        }

        // 处理特定错误码
        if (res.code === 1008) { // token过期
            const userStore = useUserStore()
            userStore.setToken(null)
            const router = useRouter()
            router.push({ name: 'login' })
        }

        ElMessage.error(res.msg || '服务异常')
        return Promise.reject(res)
    },
    (error) => {
        ElMessage.error(error.message || '网络错误')
        return Promise.reject(error)
    }
)

/**
 * 封装请求方法
 * @param {Object} config - 请求配置
 */
export function request(config) {
    return instance(config)
}

/**
 * GET请求
 * @param {String} url
 * @param {Object} params
 */
export function get(url, params) {
    return request({
        url,
        method: 'get',
        params
    })
}

/**
 * POST请求
 * @param {String} url
 * @param {Object} data
 */
export function post(url, data) {
    return request({
        url,
        method: 'post',
        data: JSON.stringify(data)
    })
}

/**
 * PUT请求
 * @param {String} url
 * @param {Object} data
 */
export function put(url, data) {
    return request({
        url,
        method: 'put',
        data: JSON.stringify(data)
    })
}

/**
 * DELETE请求
 * @param {String} url
 */
export function del(url) {
    return request({
        url,
        method: 'delete'
    })
}

export { baseURL }
