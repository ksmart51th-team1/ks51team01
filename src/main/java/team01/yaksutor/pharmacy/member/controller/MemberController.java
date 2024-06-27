package team01.yaksutor.pharmacy.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.pharmacy.member.dto.Member;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {


    @PostMapping("/memberInsert")
    public String insertMember(Member member) {
        //개국 관리
        return "redirect:/";
    }

    /**
     * 관리약사 가입페이지로 이동
     * @param model
     * @return
     */
    @GetMapping("/memberInsert/staff")
    public String memberStaffInsert(Model model){
        model.addAttribute("title","관리약사 회원가입");
        return "user/pharmacy/member/memberStaffInsert";

    }

    /**
     * 개국약사 가입페이지로 이동
     * @return
     */
    @GetMapping("/memberInsert/owner")
    public String memberOwnerInsert(Model model) {
        model.addAttribute("title", "개국약사 회원가입");

        return "user/pharmacy/member/memberOwnerInsert";
    }

}

