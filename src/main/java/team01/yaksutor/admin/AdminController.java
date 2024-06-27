package team01.yaksutor.admin;

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
public class AdminController {
    @GetMapping("/adminMain")
    public String adminMain(Model model) {
        model.addAttribute("title", "메인 대쉬보드");
        model.addAttribute("content", "여기가 메인");
        return "admin/adminMain";
    }
}
