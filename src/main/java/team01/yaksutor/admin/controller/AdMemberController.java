package team01.yaksutor.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/member")
public class AdMemberController {

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
