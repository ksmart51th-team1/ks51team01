package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.service.PhMedicineService;
import team01.yaksutor.pharmacy.service.PhShoppingCartService;

import java.util.List;

@Controller
@RequestMapping("/pharm")
@RequiredArgsConstructor
@Slf4j
public class PhMedicineController {

    private final PhMedicineService phMedicineService;
    private final PhShoppingCartService phShoppingCartService;

    /* ------------------------------------------------------------
     *                    의약품판매등록
     *  ------------------------------------------------------------*/


    @GetMapping("/order/orderMedicine")
    public String getSellMedicineList(Model model) {
        List<SellMedicine> sellMedicineList = phMedicineService.getSellMedicineList();

        List<ShoppingCart> shoppingCartList = phShoppingCartService.getShoppingCartList();
        model.addAttribute("title", "의약품 검색 주문");
        model.addAttribute("sellMedicineList", sellMedicineList);
        model.addAttribute("shoppingCartList", shoppingCartList);

        return "user/pharmacy/order/orderMedicine";
    }

    @GetMapping("/order/orderMedicineDetailView")
    public String orderMedicineDetailView(Model model) {
        model.addAttribute("title", "의약품 상세");
        model.addAttribute("content", "의약품 상세");
        return "user/pharmacy/order/orderMedicineDetailView";
    }

}
