package com.study.stockbot.service;

import com.study.stockbot.model.ThemeStock;
import com.study.stockbot.repository.ThemeStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeStockService {
    @Autowired
    private ThemeStockRepository themeStockRepository;

    // 모두 조회
    public List<ThemeStock> findAll() {
        return new ArrayList<>(themeStockRepository.findAll());
    }

    // 종목이름으로 관련주 조회
    public List<ThemeStock> findByName(String keywordName) {
        return new ArrayList<>(themeStockRepository.findThemeStockByKeywordName(keywordName));
    }

    // 종목이름이 DB에 있는지 확인
    public boolean exitsByKeywordName(String keywordName) {
        return themeStockRepository.existsThemeStockByKeywordName(keywordName);
    }
}
