package com.adme.admedemo.dto.video;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VideoDto {
    private String uuid;
//    private String thumbnailUrl;
//    private String videoUrl;
    private String uploadPath;
    private String ext;
    private Long fileSize;
    private String fileType;
    private byte[] fileData;
    private LocalDateTime videoDate;
}
