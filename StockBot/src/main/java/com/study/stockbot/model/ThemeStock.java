package com.study.stockbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="themeStock")
public class ThemeStock {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시퀀스, auto_increment

    @Column(nullable = false, unique = true, length = 45)
    private String keywordName;

    @Column(nullable = true, length = 100)
    private String relatedItems;
}
