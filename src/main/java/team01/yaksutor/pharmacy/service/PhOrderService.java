package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.Order;
import team01.yaksutor.dto.SellerInfo;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.mapper.PhOrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhOrderService {

    private final PhOrderMapper phOrderMapper;

    @Transactional
    public void submitOrder(List<ShoppingCart> shoppingCart) {
        for (ShoppingCart cart : shoppingCart) {
            SellerInfo sellerInfo = phOrderMapper.getSellerInfoByGoodsCode(cart.getGoodsCode());
            Order order = new Order();
            order.setMIdOrder(cart.getOMId());
            order.setMIdSeller(sellerInfo.getSuppId());
            order.setShoppingCartGroup("group1");
            order.setSuppPharCode(sellerInfo.getSuppCode());



        }
    }
}
