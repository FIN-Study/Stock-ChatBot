package com.study.stockbot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.stockbot.controller.api.replyData;
import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.service.ThemeStockService;
import com.study.stockbot.wrapper.QuickReply;
import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.SkillTemplate;
import com.study.stockbot.wrapper.skill.ListCardView;
import com.study.stockbot.wrapper.skill.type.ListCard;
import com.study.stockbot.wrapper.type.ListItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ThemeStockController {

    final ThemeStockService themeStockService;

    public ThemeStockController(ThemeStockService themeStockService) {
        this.themeStockService = themeStockService;
    }


    @PostMapping("/api/theme")
    public SkillResponse test(@RequestBody Map<String, Object> params) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(params);
        System.out.println(jsonInString); // request값 확인

//        사용자 발화(입력)값 확인
        HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");
        String utter = userRequest.get("utterance").toString().replace("\n", "");

//        먼저 사용자 요청 키워드가 DB에 있는지 없는지 확인
        boolean validKeyword = themeStockService.exitsByKeywordName(utter);

        if (!validKeyword) {
            return replyData.noData;
        } else {
            String headerTitle = utter + " 관련주 입니다."; // 메시지 메인 Title

//            // 모든 주식종목 조회
//            List<Stock> stockData = stockService.findAll();
//
//            for (Stock data : stockData) {
//                String stockName = data.getStockName();
//                String relatedItems = data.getRelatedItems();
//
//                String description = String
//                        .format("주식 : %s / 관련종목 : %s", stockName, relatedItems);
//
//                stockListData.add(ListItem.builder()
//                        .title((String) utter)
//                        .description(description)
//                        .build());
//            }

//        특정 키워드로 주식종목 조회
            List<ThemeStock> themeStockData = themeStockService.findByName(utter);
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


//        QuickReply stock1 = QuickReply.builder()
//                .label("테마주 검색")
//                .action("block")
//                .blockId("")
//                .build();


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


    }
}
