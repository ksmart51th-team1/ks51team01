package team01.yaksutor.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/member")
public class AdMemberController {
    @GetMapping("/memberInsert")
    public String memberInsert(Model model) {
        model.addAttribute("title","약사회원추가");
        return "admin/pharmacy/memberInsert";
    }
}
