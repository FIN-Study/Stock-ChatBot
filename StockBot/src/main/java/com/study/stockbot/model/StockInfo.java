package com.study.stockbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "stock")
public class StockInfo {

    @Id // Primary Key
    @Column(nullable = true, unique = true, length = 255)
    private String stockcode;

    @Column(nullable = true, unique = true, length = 255)
    private String stockname;

    @Column(nullable = true, length = 255)
    private String time;

    @Column(nullable = true, length = 255)
    private String price;

    @Column(nullable = true, length = 255)
    private String rate;
}
