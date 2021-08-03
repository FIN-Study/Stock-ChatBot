package com.study.stockbot.wrapper.skill;

import com.study.stockbot.wrapper.skill.type.Carousel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class CarouselView implements Skills{

    private Carousel carousel;
}
