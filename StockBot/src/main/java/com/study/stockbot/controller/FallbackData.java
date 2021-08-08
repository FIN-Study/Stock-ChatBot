package com.study.stockbot.controller;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.wrapper.QuickReply;
import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.SkillTemplate;
import com.study.stockbot.wrapper.skill.ListCardView;
import com.study.stockbot.wrapper.skill.type.ListCard;
import com.study.stockbot.wrapper.type.ListItem;

import java.util.ArrayList;
import java.util.List;

public class FallbackData {

    public SkillResponse themeStock(String utter, List<ThemeStock> themeStockData) {

        String headerTitle = utter + " 관련주 입니다."; // 메시지 메인 Title

//        특정 키워드로 주식종목 조회
        List<ListItem> stockListData = new ArrayList<>();

//        메시지 아래 관련 종목 버튼 만들기
        final QuickReply[] replyArray = new QuickReply[5];

        for (ThemeStock data : themeStockData) {
//            String stockName = data.getStockName();
            String[] relatedItems = data.getRelatedItems().split("\\s*,\\s*");
//            System.out.println(relatedItems);
            int num = 0;
            for (String item : relatedItems) {
                String title = String
                        .format("%s", item);

                stockListData.add(ListItem.builder()
                        .title((String) title)
                        .description("")
                        .build());

                replyArray[num] = QuickReply.builder()
                        .label((String) title)
                        .action("block")
                        .blockId("")
                        .build();
                num += 1;
            }
        }

        return SkillResponse.builder()
                .template(SkillTemplate.builder()
                        .addOutput(ListCardView.builder()
                                .listCard(ListCard.builder()
                                        .header(ListItem.builder()
                                                .title(headerTitle)
                                                .build())
                                        .items(stockListData)
                                        .build())
                                .build())
                        .addQuickReply(replyArray[0])
                        .addQuickReply(replyArray[1])
                        .addQuickReply(replyArray[2])
                        .addQuickReply(replyArray[3])
                        .addQuickReply(replyArray[4])
                        .addQuickReply(replyData.gotoHome)
                        .build())
                .build();
    }

    public SkillResponse stock(String utter, List<StockInfo> stockData) {
        String headerTitle = utter + " 관련 정보입니다."; // 메시지 메인 Title

//         주식종목 조회
        List<ListItem> stockDataList = new ArrayList<>();

        for (StockInfo data : stockData) {
//            String stockName = data.getStockName();
            String stockPrice = data.getPrice();
            String stockRate = data.getFlucRate();

            String description = String
                    .format("가격 : %s / 등락율 : %s", stockPrice, stockRate);

            stockDataList.add(ListItem.builder()
                    .title((String) utter)
                    .description(description)
                    .build());
        }
        return SkillResponse.builder()
                .template(SkillTemplate.builder()
                        .addOutput(ListCardView.builder()
                                .listCard(ListCard.builder()
                                        .header(ListItem.builder()
                                                .title(headerTitle)
                                                .build())
                                        .items(stockDataList)
                                        .build())
                                .build())
                        .addQuickReply(replyData.gotoHome)
                        .build())
                .build();
    }
}
