package com.study.stockbot.wrapper.skill.type;


import com.study.stockbot.wrapper.type.Social;
import com.study.stockbot.wrapper.common.Button;
import com.study.stockbot.wrapper.common.Profile;
import com.study.stockbot.wrapper.common.Thumbnail;
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
public class BasicCard implements Skills, CanCarousel {

    private String title;
    private String description;
    private Thumbnail thumbnaill;
    private Profile profile;
    private Social social;

    @Singular("addButton")
    private List<Button> buttons;
}

