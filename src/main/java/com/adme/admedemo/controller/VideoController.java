package com.adme.admedemo.controller;

import com.adme.admedemo.domain.VideoFile;
import com.adme.admedemo.dto.video.VideoRequestDto;
import com.adme.admedemo.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/tenseconds")
public class VideoController {

    public final VideoService videoService;

    /**
     * 목록 화면
     *
     * @return videoService.getList()
     * @throws Exception
     */
    @GetMapping( "/videosList")
    public List<VideoFile> listFiles() throws Exception {
        log.info("[VideoController] GetList");
        return videoService.getList();
    }

    @PostMapping("/video")
    public VideoFile uploadFile(@RequestPart(name="sideData") VideoRequestDto requestDto,
                                @RequestPart(name="videoFile") MultipartFile file) throws Exception {
        log.info("[VideoController] uploadFile() ");
        log.info("[VideoController] title : "+ requestDto.getTitle());
        log.info("[VideoController] content : "+ requestDto.getContent());
        return videoService.uploadFile(file);
    }
}
