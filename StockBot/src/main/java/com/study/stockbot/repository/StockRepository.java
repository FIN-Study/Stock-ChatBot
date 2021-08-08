package com.study.stockbot.repository;

import com.study.stockbot.model.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StockRepository extends JpaRepository<StockInfo, Long> {

    List<StockInfo> findStockInfoByStockName(String stockName);

    boolean existsStockByStockName(String stockName);
}
