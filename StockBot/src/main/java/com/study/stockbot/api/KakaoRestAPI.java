package com.study.stockbot.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class KakaoRestAPI {

    //카카오톡 오픈빌더로 리턴할 스킬 API
    @RequestMapping(value = "/kkoChat", method = {RequestMethod.POST, RequestMethod.GET}, headers = {"Accept=application/json"})
    public String callAPI(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(params);
            System.out.println(jsonInString);
            int x = 0;
        } catch (Exception e) {

        }
        return "index";
    }
//    // 키보드
//    @RequestMapping(value = "/keyboard", method = RequestMethod.GET)
//    public String keyboard() {
//
//        System.out.println("/keyboard");
//
//        JSONObject jobjBtn = new JSONObject();
//        /*JSONArray jarray = new JSONArray();
//        jarray.add("선택1");
//        jarray.add("선택2");
//        jarray.add("선택3");*/
//
//        jobjBtn.put("type", "text");
//        //jobjBtn.put("buttons", jarray);
//
//        return jobjBtn.toJSONString();
//    }


}