package com.study.stockbot.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.stockbot.api.replyData;
import com.study.stockbot.wrapper.SkillResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class KakaoRestAPI {

    @PostMapping("/home") // 기본 메인 화면
    public SkillResponse home(@RequestBody Map<String, Object> params) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(params);
            System.out.println(jsonInString);
            int x = 0;
        } catch (Exception e) {

        }
        return replyData.homeResponse;
    }
}

