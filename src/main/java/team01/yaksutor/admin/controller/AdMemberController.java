package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.mapper.AdMemberMapper;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.License;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Pharmacy;


@Controller
@RequestMapping("/admin/member")
@Slf4j
@RequiredArgsConstructor
public class AdMemberController {

    public final AdMemberMapper admembermapper;


    @PostMapping("/AdmemberInsert")
    public String AdMemberInsert(Member member, Pharmacy pharmacy, License license, Model model){

        //license와 pharmacy에 meberId주입
        license.setPharmacistId(member.getMemberId());
        pharmacy.setPharId(member.getMemberId());

        log.info("member {}",member); //얘네가 html에서 name에 해당되는  DTO에 매핑되어서 저장
        log.info("pharmacy {}",pharmacy);
        log.info("license {}",license);

        //서비스

        return "redirect:/admin/member/memberInsert";
    }


    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam(value = "memberId") String memberId){
        log.info("아이디중복체크: memberId {}",memberId);
        boolean isMember = false;
        isMember = admembermapper.idCheck(memberId);
        return isMember;
    }

    @GetMapping("/pharmacyInsert")
    public String pharmacyInsert(Model model) {

        model.addAttribute("title","약국추가");
        model.addAttribute("content","약국추가");

        return "admin/pharmacy/pharmacyInsert";
    }

    @GetMapping("/memberInsert")
    public String memberInsert(Model model) {
        model.addAttribute("title","약사회원추가");
        model.addAttribute("content","약사회원등록");
        return "admin/pharmacy/memberInsert";
    }
}
