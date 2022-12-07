package com.adme.admedemo.dto.video;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class VideoRequestDto {
    private String title;
    private String content;
}
