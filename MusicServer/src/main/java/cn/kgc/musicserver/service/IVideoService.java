package cn.kgc.musicserver.service;

import cn.kgc.musicserver.domain.dto.Result;
import cn.kgc.musicserver.domain.entity.Video;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface IVideoService {
    Result<Page<Video>> getVideosByPage(String title, Integer regionId, int page, int size);
    Result<String> addVideo(Video video);
    Result<String> updateVideo(Video video);
    Result<String> deleteVideo(Integer id);
}
