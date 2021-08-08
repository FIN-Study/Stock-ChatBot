package com.study.stockbot.model;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 AUTO_INCREMENT를 그대로 사용
    private long pid;

    @Column(nullable = false, unique = true, length = 100)
    private String keyword;

    @Column(nullable = false, length = 1000)
    private String stocks;



}
