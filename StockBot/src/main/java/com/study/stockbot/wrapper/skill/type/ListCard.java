package com.study.stockbot.wrapper.skill.type;

import com.study.stockbot.wrapper.type.ListItem;
import com.study.stockbot.wrapper.common.Button;
import com.study.stockbot.wrapper.skill.Skills;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ListCard implements Skills {

    private ListItem header;
    private List<ListItem> items;
    private List<Button> buttons;
}
