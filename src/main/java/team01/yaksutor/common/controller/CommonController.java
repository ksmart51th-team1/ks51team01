package team01.yaksutor.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class CommonController {

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin/login/adminLogin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/supplierLogin")
    public String supplierLogin() {
        return "supplierLogin";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "한국스마트정보교육원 51기 1팀 약수터 프로젝트");
        return "index";
    }
}
