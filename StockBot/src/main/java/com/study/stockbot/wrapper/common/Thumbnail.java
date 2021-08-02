package com.study.stockbot.wrapper.common;

import com.study.stockbot.wrapper.common.subtype.Link;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Thumbnail {
    private String imageUrl;
    private Link link;
    private Boolean fixedRaio;
    private Integer width;
    private Integer height;

}
