package com.study.stockbot.wrapper.type;

import com.study.stockbot.wrapper.common.subtype.Link;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ListItem {

    private String title;
    private String description;
    private String imageUrl;
    private Link link;

}
