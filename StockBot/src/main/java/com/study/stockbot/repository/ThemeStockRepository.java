package com.study.stockbot.repository;

import com.study.stockbot.model.ThemeStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ThemeStockRepository extends JpaRepository<ThemeStock, Long> {

    List<ThemeStock> findThemeStockByKeywordName(String keywordName);
}
