package cn.kgc.musicserver.service.impl;

import cn.kgc.musicserver.domain.dto.Result;
import cn.kgc.musicserver.domain.entity.Video;
import cn.kgc.musicserver.mapper.VideoMapper;
import cn.kgc.musicserver.service.IVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements IVideoService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public Result<Page<Video>> getVideosByPage(String title, Integer regionId, int page, int size) {
        try {
            QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(title)) {
                queryWrapper.like("v_name", title); // 使用数据库字段名
            }
            if (regionId != null) {
                queryWrapper.eq("regionid", regionId);
            }
            Page<Video> pageInfo = new Page<>(page, size);
            videoMapper.selectPage(pageInfo, queryWrapper);
            return Result.success(pageInfo);
        } catch (Exception e) {
            return Result.error("500", "视频查询失败: " + e.getMessage());
        }
    }

    @Override
    public Result<String> deleteVideo(Integer id) {
        try {
            int result = videoMapper.deleteById(id);
            return result > 0
                    ? Result.success("删除成功")
                    : Result.error("404", "视频不存在");
        } catch (Exception e) {
            return Result.error("500", "删除失败: " + e.getMessage());
        }
    }
}
