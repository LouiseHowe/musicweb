<script setup>
import { ref } from 'vue'
import { getVideos, deleteVideo } from '@/api/video'

// 视频数据
const videos = ref([])
const loading = ref(false)

// 查询参数
const queryParams = ref({
  pageNo: 1,
  size: 8,
  title: ''
})

// 获取视频列表
const fetchVideos = async () => {
  loading.value = true
  try {
    const { data } = await getVideos(queryParams.value)
    videos.value = data.records || []

  } catch (error) {
    console.error('获取视频列表失败:', error)
  } finally {
    loading.value = false
  }
}

// 初始化加载
fetchVideos()

// 删除视频
const handleDelete = async (id) => {
  try {
    await deleteVideo(id)
    ElMessage.success('删除成功')
    fetchVideos()
  } catch (error) {
    ElMessage.error('删除失败')
  }
}
</script>

<template>
  <div class="video-container">
    <!-- 搜索栏 -->
    <el-card shadow="never" class="search-card">
      <el-form :inline="true" :model="queryParams">
        <el-form-item label="视频名称">
          <el-input
              v-model="queryParams.title"
              placeholder="请输入视频名称"
              clearable
              @keyup.enter="fetchVideos"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="fetchVideos">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 视频列表 -->
    <div class="video-list">
      <el-row :gutter="20">
        <el-col
            v-for="video in videos"
            :key="video.id"
            :xs="12" :sm="8" :md="6" :lg="4"
            class="video-item"
        >
          <el-card :body-style="{ padding: '0px' }" shadow="hover">
            <!-- 视频封面 -->
            <img
                :src="video.coverUrl || 'https://via.placeholder.com/300x170?text=No+Cover'"
                class="video-cover"
            />

            <!-- 视频信息 -->
            <div style="padding: 14px;">
              <div class="video-title">{{ video.title }}</div>
              <div class="video-author">作者: {{ video.author || '未知' }}</div>

              <!-- 操作按钮 -->
              <div class="video-actions">
                <el-button
                    type="danger"
                    size="small"
                    @click="handleDelete(video.id)"
                >
                  删除
                </el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 加载状态 -->
    <el-empty v-if="!loading && videos.length === 0" description="暂无视频数据" />
  </div>
</template>

<style scoped>
.video-container {
  padding: 20px;
}

.search-card {
  margin-bottom: 20px;
}

.video-list {
  margin-top: 20px;
}

.video-item {
  margin-bottom: 20px;
}

.video-cover {
  width: 100%;
  height: 170px;
  object-fit: cover;
}

.video-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.video-author {
  font-size: 14px;
  color: #888;
  margin-bottom: 12px;
}

.video-actions {
  display: flex;
  justify-content: flex-end;
}
</style>
