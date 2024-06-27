package team01.yaksutor.pharmacy.pharmstrock.Controller;

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
    @GetMapping("/board")
    public String board() {

        return "user/pharmacy/board/boardList";
    }
    @GetMapping("/contact")
    public String contact() {

        return "user/pharmacy/board/contact";
    }
    @GetMapping("/notice")
    public String notice() {

        return "user/pharmacy/board/notice";
    }
    @GetMapping("/faq")
    public String faq() {

        return "user/pharmacy/board/faq";
    }
    @GetMapping("/qna")
    public String qna() {

        return "user/pharmacy/board/qna";
    }
    @GetMapping("/myPage")
    public String myPage() {

        return "user/pharmacy/myPage/myPage";
    }
    @GetMapping("/cart")
    public String cart() {

        return "user/pharmacy/order/cart";
    }

    @GetMapping("/myOrderList")
    public String myOderList() {

        return "user/pharmacy/myPage/myOrderList";
    }
    @GetMapping("/myDelivery")
    public String myDelivery() {

        return "user/pharmacy/myPage/myDelivery";
    }


}
