package team01.yaksutor.pharmacy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pharm")
@Controller
public class PharmacyController {
    @GetMapping("/main")
    public String pharmMain(Model model) {
        model.addAttribute("content", "유저 메인.");
        return "user/pharmacy/pharmMain";
    }

}
