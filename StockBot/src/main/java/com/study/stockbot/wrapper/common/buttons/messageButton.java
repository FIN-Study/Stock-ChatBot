package com.study.stockbot.wrapper.common.buttons;

import com.study.stockbot.wrapper.common.Button;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class messageButton implements Button {

    @Builder.Default
    private String action = "message";
    private String messageText;
}
