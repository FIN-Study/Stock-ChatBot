package com.study.stockbot.controller;

import com.study.stockbot.controller.api.replyData;
import com.study.stockbot.model.Member;
import com.study.stockbot.repository.MemberRepository;
import com.study.stockbot.wrapper.SkillResponse;
import com.study.stockbot.wrapper.SkillTemplate;
import com.study.stockbot.wrapper.skill.ListCardView;
import com.study.stockbot.wrapper.skill.type.ListCard;
import com.study.stockbot.wrapper.type.ListItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping("/v1") // version1의 API
public class MemberController {
    private final MemberRepository memberRepository;

    @PostMapping("member")
    public SkillResponse findMember(@RequestBody Map<String, Object> params) {
        List<Member> memberData = memberRepository.findAll();
        System.out.println(memberData);


        List<ListItem> memberListData = new ArrayList<>();

        for (Member data : memberData) {
            String userEmail = data.getKeyword();
            String userName = data.getStocks();

            String description = String
                    .format("이름 : %s / 이메일 : %s", userName, userEmail);

            memberListData.add(ListItem.builder()
                    .title((String) "DB TEST")
                    .description(description)
                    .build());
        }

        return SkillResponse.builder()
                .template(SkillTemplate.builder()
                        .addOutput(ListCardView.builder()
                                .listCard(ListCard.builder()
                                        .header(ListItem.builder()
                                                .title("DB조회 테스트")
                                                .build())
                                        .items(memberListData)
                                        .build())
                                .build())
                        .addQuickReply(replyData.todayCafe)
                        .build())
                .build();

    }

//
//    /**
//     * 멤버 조회 * @return
//     */
//    @PostMapping("member")
//    public List<MemberEntity> findAllMember() {
//        return memberRepository.findAll();
//    }


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
