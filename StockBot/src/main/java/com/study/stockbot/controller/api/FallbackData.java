package com.study.stockbot.controller.api;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.model.ThemeStockInfo;
import com.study.stockbot.service.StockService;
import com.study.stockbot.wrapper.QuickReply;
import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.SkillTemplate;
import com.study.stockbot.wrapper.skill.ListCardView;
import com.study.stockbot.wrapper.skill.type.ListCard;
import com.study.stockbot.wrapper.type.ListItem;

import java.util.ArrayList;
import java.util.List;

public class FallbackData {
    final StockService stockService;

    public FallbackData(StockService stockService) {
        this.stockService = stockService;
    }

    public SkillResponse themeStock(String utter, List<ThemeStock> themeStockData, List<ThemeStockInfo> stockDatas) {
        String headerTitle = utter + " 관련주 입니다."; // 메시지 메인 Title

//        특정 키워드로 주식종목 조회
        List<ListItem> stockListData = new ArrayList<>();

        //        메시지 아래 관련 종목 버튼 만들기
        final QuickReply[] replyArray = new QuickReply[5];

        for (ThemeStock data : themeStockData) {
//            String stockName = data.getStockName();
            String[] relatedItems = data.getRelatedItems().split("\\s*,\\s*");

            int num = 0;
            for (String item : relatedItems) {
                // 주식 정보 가져오기
//                List<StockInfo> stockData = stockService.findByName(item);
                String info_price = "";
                for (ThemeStockInfo stockData : stockDatas) {
                    if (item.equals(stockData.getStockName())) {
                        String stockPrice = stockData.getPrice();
                        String stockRate = stockData.getRate();
                        info_price = String
                                .format("가격 : %s원 | 등락율 : %s", stockPrice, stockRate);
                        break;
                    }
                }

                String title = String
                        .format("%s", item);

                stockListData.add(ListItem.builder()
                        .title((String) title)
                        .description(info_price)
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
                        .addQuickReply(replyData.gotoHome)
                        .addQuickReply(replyArray[0])
                        .addQuickReply(replyArray[1])
                        .addQuickReply(replyArray[2])
                        .addQuickReply(replyArray[3])
                        .addQuickReply(replyArray[4])
                        .build())
                .build();
    }

    public SkillResponse stock(String utter, List<StockInfo> stockData, List<String> replyLists) {
        //        String headerTitle = utter + " 관련 정보입니다."; // 메시지 메인 Title

        //        메시지 아래 관련 종목 버튼 만들기
        final QuickReply[] replyArray = new QuickReply[5];
        for (int num = 0; num < replyLists.size(); num++) {
            replyArray[num] = QuickReply.builder()
                    .label((String) replyLists.get(num))
                    .action("block")
                    .blockId("")
                    .build();
        }
//         주식종목 조회
        List<ListItem> stockDataList = new ArrayList<>();

        for (StockInfo data : stockData) {
//            String stockName = data.getStockName();
            String stockPrice = data.getPrice();
            String stockRate = data.getRate();
            String updateTime = data.getTime();

            String title = String
                    .format("가격 : %s원\n등락율 : %s", stockPrice, stockRate);
            String description = String
                    .format("Update on. %s", updateTime);

            stockDataList.add(ListItem.builder()
                    .title((String) title)
                    .description(description)
                    .build());
        }
        return SkillResponse.builder()
                .template(SkillTemplate.builder()
                        .addOutput(ListCardView.builder()
                                .listCard(ListCard.builder()
                                        .header(ListItem.builder()
                                                .title(utter)
                                                .build())
                                        .items(stockDataList)
                                        .build())
                                .build())
                        .addQuickReply(replyData.gotoHome)
                        .addQuickReply(replyArray[0])
                        .addQuickReply(replyArray[1])
                        .addQuickReply(replyArray[2])
                        .addQuickReply(replyArray[3])
                        .addQuickReply(replyArray[4])
                        .build())
                .build();
    }
}
