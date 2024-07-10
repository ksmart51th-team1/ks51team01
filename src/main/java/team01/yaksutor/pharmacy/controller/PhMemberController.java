package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.License;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Pharmacy;
import team01.yaksutor.pharmacy.service.PhMemberService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class PhMemberController {

    private final PhMemberService phMemberService;


    @PostMapping("/memberInsert/staff")
    public String insertStaff(Member member,Pharmacy pharmacy, License license) {
        log.info("Member {}",member);
        log.info("Pharmacy {}",pharmacy);
        log.info("License {}",license);
        phMemberService.insertStaffMember(member,license,pharmacy);
        return "redirect:/";
    }

    /**
     * 개국약사의 회원가입 처리
     * @param member
     * @param pharmacy
     * @param license
     * @return
     */
    @PostMapping("/memberInsert/owner")
    public String insertOwner(Member member, Pharmacy pharmacy, License license) {
        log.info("Member : {}",member);
        log.info("License : {}",license);
        log.info("pharmacy : {}",pharmacy);

        phMemberService.insertOwnerMember(member,license,pharmacy);

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

