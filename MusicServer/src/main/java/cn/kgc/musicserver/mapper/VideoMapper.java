package cn.kgc.musicserver.mapper;

import cn.kgc.musicserver.domain.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface VideoMapper extends BaseMapper<Video> {
    Page<Video> selectVideoPage(Page<Video> page, @Param("title") String title);
}
