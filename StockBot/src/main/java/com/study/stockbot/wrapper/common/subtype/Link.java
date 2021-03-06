package com.study.stockbot.wrapper.common.subtype;

import lombok.*;

@Getter
@Builder
@ToString
public class Link {

    private String mobile;
    private String ios;
    private String android;
    private String pc;
    private String mac;
    private String win;
    private String web;
}
