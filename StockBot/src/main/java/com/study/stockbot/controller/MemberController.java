package com.study.stockbot.controller;

import com.study.stockbot.entity.MemberEntity;
import com.study.stockbot.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping("/v1") // version1의 API
public class MemberController {
    private final MemberRepository memberRepository;

    /** * 멤버 조회 * @return */
    @GetMapping("member")
    public List<MemberEntity> findAllMember()
    {
        return memberRepository.findAll();
    }

//    @PostMapping("member")
//    public MemberEntity signUp()
//    {
//        final MemberEntity member = MemberEntity.builder()
//                .username("test_user@gmail.com")
//                .name("test user")
//                .build();
//        return memberRepository.save(member);
//    }


}
