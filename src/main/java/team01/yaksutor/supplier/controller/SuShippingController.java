package team01.yaksutor.supplier.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.dto.Shipping;
import team01.yaksutor.supplier.mapper.SuShippingMapper;

import java.util.List;

@Controller
@RequestMapping("/supplier")
@Slf4j
@RequiredArgsConstructor
public class SuShippingController {
    private final HttpServletRequest request;
    private final SuShippingMapper suShippingMapper;


    @GetMapping("/shipSearchList")
    public String shipSearchList(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String suppCode = suShippingMapper.getSuppCode(sid);
        List<Shipping> shipList = suShippingMapper.getShippingList(suppCode);
        log.info("orderShip: {}", shipList);

        model.addAttribute("shipList", shipList);
        model.addAttribute("title", "주문 배송 목록");
        model.addAttribute("content", "주문 배송 목록");

        return "user/supplier/shipping/myShipSearchList";
    }

    @GetMapping("/refundShipList")
    public String refundShipList(Model model){
        String sid = request.getSession().getAttribute("S_ID").toString();
        String suppCode = suShippingMapper.getSuppCode(sid);
        List<Shipping> shipList = suShippingMapper.getRefundShippingList(suppCode);
        log.info("refundShip: {}", shipList);

        model.addAttribute("shipList", shipList);
        model.addAttribute("title", "반품 배송 목록");
        model.addAttribute("content", "반품 배송 목록");

        return "user/supplier/shipping/myRefundShipList";
    }

    @GetMapping("/myShippingView")
    public String shippingDetailView(@RequestParam(value="shipCode") String shipCode){
        log.info("shipCode: {}", shipCode);


        return null;
    }
}
