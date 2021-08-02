package com.study.stockbot.controller;

import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.skill.SimpleTextView;
import com.study.stockbot.wrapper.skill.type.SimpleText;
import com.study.stockbot.wrapper.QuickReply;
import com.study.stockbot.wrapper.SkillTemplate;

public interface replyData {
    QuickReply todayCafe = QuickReply.builder()
            .label("\uD83C\uDF72테마주 검색")
            .action("block")
            .blockId("5e12c4c8ffa74800014bddbd")
            .build();

    SkillResponse homeResponse = SkillResponse.builder()
            .template(SkillTemplate.builder()
                    .addOutput(SimpleTextView.builder()
                            .simpleText(SimpleText.builder()
                                    .text("아래 버튼을 눌러주세요")
                                    .build()
                            )
                            .build())
                    .addQuickReply(replyData.todayCafe)
                    .build())
            .build();
}
