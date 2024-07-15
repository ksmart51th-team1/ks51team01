package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.Member;
import team01.yaksutor.pharmacy.service.phMyPageService;

@RequestMapping("/pharm")
@RequiredArgsConstructor
@Controller
public class PharmacyController {

    private static final Logger log = LoggerFactory.getLogger(PharmacyController.class);
    private final phMyPageService phMyPageService;

    @GetMapping("/main")
    public String pharmMain(Model model) {
        model.addAttribute("content", "유저 메인.");
        return "user/pharmacy/pharmMain";
    }

    @GetMapping("/contact")
    public String contact() {

        return "user/pharmacy/board/contact";
    }

    @GetMapping("/qna")
    public String qna() {

        return "user/pharmacy/board/qna";
    }
    @GetMapping("/myPage")
    public String myPage(Model model) {
        Member member = phMyPageService.getMeberInfoById();
        model.addAttribute("member",member);
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
    @GetMapping("/liveOrder")
    public String liveOrder() {

        return "user/pharmacy/order/liveOrder";
    }
    @GetMapping("/liveOrderList")
    public String liveOrderList() {

        return "user/pharmacy/order/liveOrderList";
    }
    @GetMapping("/hotDeal")
    public String hotDeal() {

        return "user/pharmacy/order/hotDeal";
    }


}
