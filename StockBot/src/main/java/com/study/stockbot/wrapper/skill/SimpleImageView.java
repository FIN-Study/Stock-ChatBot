package com.study.stockbot.wrapper.skill;

import com.study.stockbot.wrapper.skill.type.SimpleImage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SimpleImageView implements Skills {

    private SimpleImage simpleImage;
}
