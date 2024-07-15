package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.admin.service.AdMemberService;
import team01.yaksutor.dto.Member;

@Controller
@RequestMapping("/pharm/myPage")
@RequiredArgsConstructor
@Slf4j
public class PhMyPageController {


    private final team01.yaksutor.pharmacy.service.phMyPageService phMyPageService;

    @PostMapping("/myAccountModify")
    public String myAccountModify(Member member){
       phMyPageService.modifyMyAccount(member);
        return "redirect:/pharm/myPage";
    }
}