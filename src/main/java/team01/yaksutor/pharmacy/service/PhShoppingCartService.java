package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.mapper.PhShoppingCartMapper;

import java.util.List;

@Service("PhShoppingCartService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhShoppingCartService {
    private final PhShoppingCartMapper phShoppingCartMapper;

    /*============================================
             장바구니
    ============================================*/

    // 조회
    public List<ShoppingCart> getShoppingCartList(){
        List<ShoppingCart> shoppingCartList = phShoppingCartMapper.getShoppingCartList();
        return shoppingCartList;
    }
    // 추가
    public void addShoppingCart(ShoppingCart shoppingCart) {
        phShoppingCartMapper.addShoppingCart(shoppingCart);
    }
    // 삭제
    public void deleteShoppingCart(String goodsCode, String oMID) {
        phShoppingCartMapper.deleteShoppingCart(goodsCode, oMID);
    }

    //특정아이디로 장바구니 조회
    public List<ShoppingCart> getCartItemsByMemberId(String memberId) {
        return phShoppingCartMapper.findByOMId(memberId);
    }
}

