package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.service.PhShoppingCartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PhShoppingCartController {

    private final PhShoppingCartService phShoppingCartService;

    /* ------------------------------------------------------------
     *                    장바구니
     *  ------------------------------------------------------------*/
    // 조회
    @GetMapping("/pharm/cart")
    public String getShoppingCartList(Model model) {
        List<ShoppingCart> shoppingCartList = phShoppingCartService.getShoppingCartList();

        model.addAttribute("shoppingCartList", shoppingCartList);


        return "user/pharmacy/order/cart";
    }





}
