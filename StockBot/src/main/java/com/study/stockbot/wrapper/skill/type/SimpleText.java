package com.study.stockbot.wrapper.skill.type;

import com.study.stockbot.wrapper.skill.Skills;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SimpleText implements Skills {
    private String text;
}
