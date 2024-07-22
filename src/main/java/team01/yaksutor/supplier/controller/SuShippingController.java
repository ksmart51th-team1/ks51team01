package team01.yaksutor.supplier.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
@Slf4j
@RequiredArgsConstructor
public class SuShippingController {
    private final HttpServletRequest request;


    @GetMapping("/shipSearchList")
    public String shipSearchList() {
        String sid = request.getSession().getAttribute("S_ID").toString();


        return "user/supplier/shipping/myShipSearchList";
    }
}
