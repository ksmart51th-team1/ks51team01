package team01.yaksutor.pharmacy.pharmstrock.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PharmStrockController {

    @GetMapping("pharm/main/pharmStrock")
    public String pharmStrock() {
        return "user/pharmacy/pharmstock/pharmStrockList";
    }
}
