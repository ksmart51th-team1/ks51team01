package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdMemberController {
    @GetMapping("/member/memberInsert")
    public String memberInsert(Model model) {
        model.addAttribute("title","약사회원추가");
        model.addAttribute("content","새로운 회원 등록");
        return "admin/pharmacy/memberInsert";
    }
}
