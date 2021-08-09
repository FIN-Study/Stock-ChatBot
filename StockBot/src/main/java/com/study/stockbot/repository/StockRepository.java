package com.study.stockbot.repository;

import com.study.stockbot.model.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StockRepository extends JpaRepository<StockInfo, Long> {

    List<StockInfo> findStockInfoByStockname(String stockName); // 종목이름으로 DB에서 검색

    boolean existsStockByStockname(String stockName); // 종목이름이 DB에 있는지 없는지
}
