package com.study.stockbot.wrapper.skill;


import com.study.stockbot.wrapper.skill.type.SimpleText;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SimpleTextView implements Skills {

    private SimpleText simpleText;
}
