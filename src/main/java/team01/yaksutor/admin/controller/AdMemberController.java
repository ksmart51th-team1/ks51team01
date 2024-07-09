package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.mapper.AdMemberMapper;
import team01.yaksutor.admin.service.AdMemberService;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.License;
import team01.yaksutor.dto.ManagePharmacy;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Pharmacy;


@Controller
@RequestMapping("/admin/member")
@Slf4j
@RequiredArgsConstructor
public class AdMemberController {

    private final AdMemberMapper admembermapper;
    private final PasswordEncoder passwordEncoder;
    private final AdMemberService adMemberService;


    @PostMapping("/AdmemberInsert")
    public String AdMemberInsert(Member member, Pharmacy pharmacy, License license, Model model){

        //license와 pharmacy에 meberId주입
        license.setPharmacistId(member.getMemberId());

        log.info("member {}",member); //얘네가 html에서 name에 해당되는  DTO에 매핑되어서 저장
        log.info("pharmacy {}",pharmacy);
        log.info("license {}",license);

        //서비스
        int memberLevel = member.getMemberLevelNum();
        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
        log.info("member>> {}",member);
        if(memberLevel == 3){
            pharmacy.setPharId(member.getMemberId());
            adMemberService.insertOwnerMember(member,license,pharmacy);
        } else if (memberLevel == 4){
            ManagePharmacy managePharmacy = new ManagePharmacy();
            //pharCode 조회
            String pid = pharmacy.getPharId();
            String pname = pharmacy.getPName();
            String pharCode = admembermapper.getPharCode(pid);
            log.info("adadpharCode {}",pharCode);
            //managePharmacy에 기본값 셋팅
            managePharmacy.setPharCode(pharCode);
            managePharmacy.setPharId(member.getMemberId());
            log.info("managePharmacy: {}",managePharmacy);
            adMemberService.insertStaffMember(member,license,managePharmacy);
        }
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
