package team01.yaksutor.pharmacy.pharmstrock.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pharm")
@Controller
public class PharmStrockController {

    @GetMapping("/checkMedi")
    public String pharmStrock(Model model) {
        model.addAttribute("title","재고조사");
        return "user/pharmacy/pharmstock/checkMedi";
    }
}
