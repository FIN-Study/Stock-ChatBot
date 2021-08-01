package com.study.stockbot.wrapper.common.buttons;

import com.study.stockbot.wrapper.common.Button;
import com.study.stockbot.wrapper.common.subtype.Link;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class osLinkButton implements Button {

    @Builder.Default
    private String action = "osLink";
    private Link osLink;
}
