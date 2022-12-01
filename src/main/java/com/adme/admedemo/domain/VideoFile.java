package com.adme.admedemo.domain;

import com.adme.admedemo.dto.video.VideoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class VideoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// id 자동 카운트
    private Long id;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String uploadPath;

    @Column(nullable = false)
    private String ext;

    @Column(nullable = false)
    private Long fileSize; // 파일 사이즈 바이트 수

    @Column(nullable = false)
    private String fileType; // 파일 확장자

    @Column(nullable = false)
    private byte[] fileData; // 실제 파일 데티어

    @Column(nullable = false)
    private String videoDate;

    public VideoFile(VideoDto videoDto) {
        this.uuid = videoDto.getUuid();
        this.uploadPath = String.valueOf(videoDto.getUploadPath());
        this.ext = videoDto.getExt();
        this.fileSize = videoDto.getFileSize();
        this.fileType = videoDto.getFileType();
        this.fileData = videoDto.getFileData();
        this.videoDate = String.valueOf(videoDto.getVideoDate());
    }
}