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
import team01.yaksutor.pharmacy.dto.QuestionCenter;

import java.util.List;


@Controller
@RequestMapping("/admin/member")
@Slf4j
@RequiredArgsConstructor
public class AdMemberController {

    private final AdMemberMapper admembermapper;
    private final PasswordEncoder passwordEncoder;
    private final AdMemberService adMemberService;
    private final MemberMapper memberMapper;

    @GetMapping("/pharmacySearchList")
    public String pharmacySearchList(Model model) {
        List<Pharmacy> pharmacyList = adMemberService.getPharmacyInfo();
        model.addAttribute("title", "약국 조회");
        model.addAttribute("content", "약국 조회");
        model.addAttribute("pharmacyList", pharmacyList);
        return "admin/pharmacy/pharmacySearchList";
    }


    @GetMapping("/memberSearchList")
    public String memberSearchList(Model model) {
        List<Member> memberList = adMemberService.getMemberInfo();
        model.addAttribute("title","전체회원정보");
        model.addAttribute("content","전체회원정보");
        model.addAttribute("memberList",memberList);

        return "admin/pharmacy/memberSearchList";
    }


    @PostMapping("/adPharmacyInsert")
    public String AdPharmacyInsert(Member member,Pharmacy pharmacy) {
        log.info("member {}", member);
        log.info("pharmacy {}", pharmacy);
        adMemberService.staffToOwnerMember(member, pharmacy);
        return "redirect:/admin/adminMain";
    }

    @PostMapping("/AdmemberInsert")
    public String AdMemberInsert(Member member, Pharmacy pharmacy, License license, Model model) {

        //license와 pharmacy에 meberId주입
        license.setPharmacistId(member.getMemberId());

        log.info("member {}", member); //얘네가 html에서 name에 해당되는  DTO에 매핑되어서 저장
        log.info("pharmacy {}", pharmacy);
        log.info("license {}", license);

        //서비스
        int memberLevel = member.getMemberLevelNum();
        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
        log.info("member>> {}", member);
        if (memberLevel == 3) {
            pharmacy.setPharId(member.getMemberId());
            adMemberService.insertOwnerMember(member, license, pharmacy);
        } else if (memberLevel == 4) {
            ManagePharmacy managePharmacy = new ManagePharmacy();
            //pharCode 조회
            String pid = pharmacy.getPharId();
            String pname = pharmacy.getPName();
            String pharCode = admembermapper.getPharCode(pid);
            log.info("adadpharCode {}", pharCode);
            //managePharmacy에 기본값 셋팅
            managePharmacy.setPharCode(pharCode);
            managePharmacy.setPharId(member.getMemberId());
            log.info("managePharmacy: {}", managePharmacy);
            adMemberService.insertStaffMember(member, license, managePharmacy);
        }
        return "redirect:/admin/member/memberInsert";
    }


    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam(value = "memberId") String memberId) {
        log.info("아이디중복체크: memberId {}", memberId);
        boolean isMember = false;
        isMember = admembermapper.idCheck(memberId);
        return isMember;
    }

    @GetMapping("/pharmacyInsert")
    public String pharmacyInsert(Model model) {

        model.addAttribute("title", "기존 관리약사 개국약사로 변경");
        model.addAttribute("content", "기존 관리약사 개국약사로 변경");

        return "admin/pharmacy/pharmacyInsert";
    }

    @GetMapping("/memberInsert")
    public String memberInsert(Model model) {
        model.addAttribute("title", "약사회원추가");
        model.addAttribute("content", "약사회원등록");
        return "admin/pharmacy/memberInsert";
    }

    @PostMapping("/isStaff")
    @ResponseBody
    public boolean isStaff(@RequestParam(value = "memberId") String memberId) {
        boolean isStaff = false;

        // memberMapper를 통해 memberId로 회원 정보를 가져옵니다.
        Member member = null;
        try {
            member = memberMapper.getMemberInfoById(memberId);
        } catch (Exception e) {
            // 예외 발생 시 로그를 기록합니다.
            System.err.println("Error retrieving member info: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error retrieving member info", e);
        }

        // member가 null인 경우 예외를 던집니다.
        if (member == null) {
            throw new IllegalArgumentException("Member not found for ID: " + memberId);
        }

        // member의 레벨을 확인합니다.
        int memberLevel = member.getMemberLevelNum();
        System.out.println("Member Level: " + memberLevel);

        // memberLevel이 4인 경우 isStaff를 true로 설정합니다.
        if (memberLevel == 4) {
            isStaff = true;
        } else {
            isStaff = false;
        }

        System.out.println("isStaff: " + isStaff);
        return isStaff;
    }
}