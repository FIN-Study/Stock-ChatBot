package com.study.stockbot.repository;

import com.study.stockbot.model.ThemeStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ThemeStockRepository extends JpaRepository<ThemeStock, Long> {

    List<ThemeStock> findThemeStockByKeywordName(String keywordName); // 키워드로 주식종목 검색
    
    boolean existsThemeStockByKeywordName(String keywordName); // 특정 키워드가 있는지 없는지 반환
}
