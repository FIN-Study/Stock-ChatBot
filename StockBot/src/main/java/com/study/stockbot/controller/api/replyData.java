package com.study.stockbot.controller.api;

import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.skill.SimpleTextView;
import com.study.stockbot.wrapper.skill.type.SimpleText;
import com.study.stockbot.wrapper.QuickReply;
import com.study.stockbot.wrapper.SkillTemplate;

public interface replyData {
    QuickReply todayCafe = QuickReply.builder()
            .label("\uD83C\uDF72테마주 검색")
            .action("block")
            .blockId("")
            .build();

    QuickReply gotoHome = QuickReply.builder()
            .label("처음으로")
            .action("block")
            .blockId("")
            .build();


    //    DB에 데이터가 없을 경우
    SkillResponse noData = SkillResponse.builder()
            .template(SkillTemplate.builder()
                    .addOutput(SimpleTextView.builder()
                            .simpleText(SimpleText.builder()
                                    .text("데이터베이스에 존재하지 않습니다.\n입력단어를 확인하시거나, 운영자에게 등록요청을 부탁드립니다")
                                    .build()
                            )
                            .build())
                    .addQuickReply(replyData.gotoHome)
                    .build())
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
