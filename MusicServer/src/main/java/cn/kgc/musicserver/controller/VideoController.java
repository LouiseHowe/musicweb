package cn.kgc.musicserver.controller;

import cn.kgc.musicserver.domain.dto.Result;
import cn.kgc.musicserver.domain.entity.Video;
import cn.kgc.musicserver.service.IVideoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final IVideoService videoService;

    public VideoController(IVideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/list")
    public Result listVideos(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer regionId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return videoService.getVideosByPage(title, regionId, page, size);
    }

    @PostMapping("/add")
    public Result addVideo(@RequestBody Video video) {
        return videoService.addVideo(video);
    }

    @PutMapping("/update")
    public Result updateVideo(@RequestBody Video video) {
        return videoService.updateVideo(video);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteVideo(@PathVariable Integer id) {
        return videoService.deleteVideo(id);
    }
}
