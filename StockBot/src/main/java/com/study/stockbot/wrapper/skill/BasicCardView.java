package com.study.stockbot.wrapper.skill;

import com.study.stockbot.wrapper.skill.type.BasicCard;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BasicCardView implements Skills, CanCarousel {

    private BasicCard basicCard;
}
