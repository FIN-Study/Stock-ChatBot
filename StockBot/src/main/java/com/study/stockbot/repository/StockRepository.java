package com.study.stockbot.repository;

import com.study.stockbot.model.StockInfo;
import com.study.stockbot.model.ThemeStockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StockRepository extends JpaRepository<StockInfo, Long> {

    List<StockInfo> findStockInfoByStockname(String stockName); // 종목이름으로 DB에서 검색

    boolean existsStockByStockname(String stockName); // 종목이름이 DB에 있는지 없는지

    // select * from stock where stockname in ('DSR','STX','KEC','삼성전자','NAVER');
    @Query(value = "SELECT s.stockcode, s.stockname, s.price, s.rate FROM stock s WHERE s.stockname IN ?1", nativeQuery = true)
    List<ThemeStockInfo> findAllByThemeStock(List<String> stockNames);
}
