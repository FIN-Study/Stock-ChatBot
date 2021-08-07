package com.study.stockbot.service;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    // 모두 조회
    public List<StockInfo> findAll() {
        return new ArrayList<>(stockRepository.findAll());
    }

    // 종목이름으로 종목정보 조회
    public List<StockInfo> findByName(String stockName) {
        return new ArrayList<>(stockRepository.findStockInfoByStockName(stockName));
    }

}
