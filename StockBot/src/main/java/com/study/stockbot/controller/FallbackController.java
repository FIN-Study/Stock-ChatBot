package com.study.stockbot.controller;


import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.service.StockService;
import com.study.stockbot.service.ThemeStockService;
import com.study.stockbot.wrapper.SkillResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FallbackController extends FallbackData {

    final ThemeStockService themeStockService;
    final StockService stockService;


    public FallbackController(ThemeStockService themeStockService, StockService stockService) {
        this.themeStockService = themeStockService;
        this.stockService = stockService;
    }

    @PostMapping("/api/fallback")
    public SkillResponse fallback(@RequestBody Map<String, Object> params) {

//        사용자 발화(입력)값 확인
        HashMap<String, Object> userRequest = (HashMap<String, Object>) params.get("userRequest");
        String utter = userRequest.get("utterance").toString().replace("\n", "");

//        System.out.println(utter);


//        먼저 사용자 요청 키워드가 DB에 있는지 없는지 확인
        boolean validKeyword = themeStockService.exitsByKeywordName(utter);
        if (validKeyword) {
            List<ThemeStock> themeStockData = themeStockService.findByName(utter);
            return new FallbackData().themeStock(utter, themeStockData);
        }

        boolean validStock = stockService.exitsByStockName(utter);
        if (validStock) {
            List<StockInfo> stockData = stockService.findByName(utter);
            return new FallbackData().stock(utter, stockData);
        }


        return replyData.noData;
    }
}
