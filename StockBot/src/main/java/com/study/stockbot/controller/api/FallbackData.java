package com.study.stockbot.controller.api;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.model.ThemeStockInfo;
import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.SkillTemplate;
import com.study.stockbot.wrapper.common.subtype.Link;
import com.study.stockbot.wrapper.skill.ListCardView;
import com.study.stockbot.wrapper.skill.type.ListCard;
import com.study.stockbot.wrapper.type.ListItem;

import java.util.ArrayList;
import java.util.List;

public class FallbackData {

    public SkillResponse themeStock(String utter, List<ThemeStock> themeStockData, List<ThemeStockInfo> stockDatas) {
        String headerTitle = utter + " 관련주 입니다."; // 메시지 메인 Title

//        특정 키워드로 주식종목 조회
        List<ListItem> stockListData = new ArrayList<>();

        for (ThemeStock data : themeStockData) {
//            String stockName = data.getStockName();
            String[] relatedItems = data.getRelatedItems().split("\\s*,\\s*");

            for (String item : relatedItems) {
                // 주식 정보 가져오기
//                List<StockInfo> stockData = stockService.findByName(item);
                String stockCode = "";
                String info_price = "";
                for (ThemeStockInfo stockData : stockDatas) {
                    if (item.equals(stockData.getStockName())) {
                        String stockPrice = stockData.getPrice();
                        String stockRate = stockData.getRate();
                        info_price = String
                                .format("가격 : %s원 | 등락율 : %s", stockPrice, stockRate);
                        stockCode = stockData.getStockCode();
                        break;
                    }
                }

                String title = String
                        .format("%s", item);

                stockListData.add(ListItem.builder()
                        .title((String) title)
                        .description(info_price)
                        .link(Link.builder()
                                .web("https://finance.naver.com/item/main.nhn?code=" + stockCode).build())
                        .build());
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
                        .build())
                .build();
    }

    public SkillResponse stock(String utter, List<StockInfo> stockData) {
        //        String headerTitle = utter + " 관련 정보입니다."; // 메시지 메인 Title

//         주식종목 조회
        List<ListItem> stockDataList = new ArrayList<>();

        for (StockInfo data : stockData) {
            String stockCode = data.getStockcode();
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
                    .link(Link.builder()
                            .web("https://finance.naver.com/item/main.nhn?code=" + stockCode).build())
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
                        .build())
                .build();
    }
}
