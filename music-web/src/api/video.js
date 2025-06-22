import { get, del } from './request'

export function getVideos(params) {
    return get('/video/list', params)
}

export function deleteVideo(id) {
    return del(`/video/delete/${id}`)
}
