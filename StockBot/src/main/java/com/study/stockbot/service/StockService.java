package com.study.stockbot.service;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStockInfo;
import com.study.stockbot.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

//    // 모두 조회
//    public List<StockInfo> findAll() {
//        return new ArrayList<>(stockRepository.findAll());
//    }

    // 종목이름으로 종목정보 조회
    public List<StockInfo> findByName(String stockName) {
        return new ArrayList<>(stockRepository.findStockInfoByStockname(stockName));
    }

    public boolean exitsByStockName(String stockName) {
        return stockRepository.existsStockByStockname(stockName);
    }

    // 테마주 관련 모든 종목정보 조회
    public List<ThemeStockInfo> findAllByThemeStock(List<String> stockNames) {
        return new ArrayList<>(stockRepository.findAllByThemeStock(stockNames));
    }

}
