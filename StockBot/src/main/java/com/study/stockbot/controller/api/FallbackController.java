package com.study.stockbot.controller.api;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.model.ThemeStockInfo;
import com.study.stockbot.service.StockService;
import com.study.stockbot.service.ThemeStockService;
import com.study.stockbot.wrapper.SkillResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FallbackController extends FallbackData {

    final ThemeStockService themeStockService;
    final StockService stockService;

//    private List<String> quickReplyList = new ArrayList<>(); // 사용자가 관련주 검색 후, 한 종목을 검색했을 때, 다른 관련 종목 검색을 이어서 계속 할 수 있게 하기 위함.

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


//        먼저 사용자 요청 키워드가 DB에 있는지 없는지 확인 후, 조회
        boolean validKeyword = themeStockService.exitsByKeywordName(utter);


        if (validKeyword) { // 테마주 검색
            List<ThemeStock> themeStockData = themeStockService.findByName(utter);

            List<String> stockData = new ArrayList<>();
            
            // 리스트에 종목별 주식정보를 넣어주기 위함
            for (ThemeStock data : themeStockData) {
                String[] relatedItems = data.getRelatedItems().split("\\s*,\\s*");
                stockData.addAll(Arrays.asList(relatedItems));
            }
            List<ThemeStockInfo> stockDatas = stockService.findAllByThemeStock(stockData);

            return new FallbackData().themeStock(utter, themeStockData, stockDatas);
        }

        boolean validStock = stockService.exitsByStockName(utter);
        if (validStock) { // 주식종목 검색
            List<StockInfo> stockData = stockService.findByName(utter);
            return new FallbackData().stock(utter, stockData);
        }

        return replyData.noData;
    }
}
