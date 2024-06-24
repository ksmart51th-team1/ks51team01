package team01.yaksutor.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "한국스마트정보교육원 51기 1팀 약수터 프로젝트");
        return "index";
    }
}
