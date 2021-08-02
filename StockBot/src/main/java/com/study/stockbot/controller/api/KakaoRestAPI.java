package com.study.stockbot.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.stockbot.controller.replyData;
import com.study.stockbot.wrapper.SkillResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

