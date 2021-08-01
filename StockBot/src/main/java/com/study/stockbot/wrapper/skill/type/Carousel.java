package com.study.stockbot.wrapper.skill.type;

import com.study.stockbot.wrapper.common.CarouselHeader;
import com.study.stockbot.wrapper.skill.CanCarousel;
import com.study.stockbot.wrapper.skill.Skills;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class Carousel implements Skills {

    private String type;
    private CarouselHeader header;
    @Singular("addItem")
    private List<CanCarousel> items;

}
