package com.study.stockbot.wrapper;

import com.study.stockbot.wrapper.type.ContextControl;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SkillResponse {

    @Builder.Default
    private String version = "2.0";
    private SkillTemplate template;
    private ContextControl context;
    private Object data;
}

