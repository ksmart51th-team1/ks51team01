package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.common.service.MemberService;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.service.PhMemberService;
import team01.yaksutor.pharmacy.service.PhShoppingCartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PhShoppingCartController {

    private final PhShoppingCartService phShoppingCartService;
    private final MemberService memberService;

    /* ------------------------------------------------------------
     *                    장바구니
     *  ------------------------------------------------------------*/
    // 조회
    @GetMapping("/pharm/cart")
    public String getShoppingCartList(Model model) {
        List<ShoppingCart> shoppingCartList = phShoppingCartService.getShoppingCartList();
        List<Member> memberList = memberService.getMemberList();
        model.addAttribute("shoppingCartList", shoppingCartList);
        model.addAttribute("memberList", memberList);
        return "user/pharmacy/order/cart";
    }

    // 추가
    @PostMapping("/pharm/addCart")
    public String addShoppingCart(ShoppingCart shoppingCart){
        phShoppingCartService.addShoppingCart(shoppingCart);
        return "redirect:/pharm/order/orderMedicine";
    }





}
