package com.study.stockbot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.stockbot.controller.api.replyData;
import com.study.stockbot.model.StockInfo;
import com.study.stockbot.service.StockService;
import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.SkillTemplate;
import com.study.stockbot.wrapper.skill.ListCardView;
import com.study.stockbot.wrapper.skill.type.ListCard;
import com.study.stockbot.wrapper.type.ListItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StockController {

    final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/api/stock")
    public SkillResponse test(@RequestBody Map<String, Object> params) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(params);
        System.out.println(jsonInString); // request값 확인

//        사용자 발화(입력)값 확인
        HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");
        String utter = userRequest.get("utterance").toString().replace("\n", "");
        String headerTitle = utter + " 관련 정보입니다."; // 메시지 메인 Title

//         주식종목 조회
        List<StockInfo> stockData = stockService.findByName(utter);
        List<ListItem> stockDataList = new ArrayList<>();

        for (StockInfo data : stockData) {
//            String stockName = data.getStockName();
            String stockPrice = data.getPrice();
            String stockRate = data.getRate();

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
