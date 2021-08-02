package com.study.stockbot.wrapper.skill;

import com.study.stockbot.wrapper.skill.type.ListCard;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class ListCardView implements Skills{

    private ListCard listCard;
}
