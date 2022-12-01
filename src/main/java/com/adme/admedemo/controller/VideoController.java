package com.adme.admedemo.controller;

import com.adme.admedemo.domain.VideoFile;
import com.adme.admedemo.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/tanseconds")
public class VideoController {

    public final VideoService videoService;

    /**
     * 목록 화면
     *
     * @return videoService.getList()
     * @throws Exception
     */
    @GetMapping( "/videosList" )
    public List<VideoFile> listFiles() throws Exception {
        log.info("VideoController GetList");
        return videoService.getList();
    }

    @PostMapping(value = "/videos")
    public VideoFile uploadFile(@RequestParam("uploadFile") MultipartFile file) throws Exception {
        log.info("VideoController Post");
        return videoService.uploadFile(file);
    }
}
