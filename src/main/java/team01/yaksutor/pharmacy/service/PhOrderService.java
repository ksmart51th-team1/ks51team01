package team01.yaksutor.pharmacy.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.Order;
import team01.yaksutor.dto.OrderDetail;
import team01.yaksutor.dto.SellerInfo;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.mapper.PhOrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhOrderService {

    private final PhOrderMapper phOrderMapper;
    private final HttpServletRequest request;

    @Transactional
    public void submitOrder(List<ShoppingCart> shoppingCart) {

        //주문생성
        String sessionId = (String) request.getSession().getAttribute("S_ID");
        Order order = new Order();
        order.setMIdOrder(sessionId);
        order.setShoppingCartGroup("group1");
        order.setDeliveryState("배송대기");
        order.setPurchaseState("결제준비중");
        order.setPaymentMethod("카카오페이");
        order.setRequestTerm("파손주의");
        order.setOrderType("주문");

        //생성한 주문 DB에 등록
        phOrderMapper.addOrder(order);
        String oCode = order.getOCode();
        //화면으로 부터 넘겨받은 장바구니 데이터를 돌면서 주문상세에 저장
        int totalPrice = 0;
        for (ShoppingCart cart : shoppingCart) {
            SellerInfo sellerInfo = phOrderMapper.getSellerInfoByGoodsCode(cart.getGoodsCode());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOCode(oCode);
            orderDetail.setGoodsCode(cart.getGoodsCode());
            orderDetail.setOSellerId(sellerInfo.getSuppId());
            orderDetail.setSuppCode(sellerInfo.getSuppCode());
            orderDetail.setOrderQty(cart.getSumQty());
            orderDetail.setOrderPrice((cart.getSumQty() * cart.getMediPrice()));
            System.out.println(orderDetail);
            phOrderMapper.addOrderDetail(orderDetail);

            totalPrice = totalPrice + cart.getSumQty() * cart.getMediPrice();
        }

        order.setOrderTotalPrice(totalPrice);
        System.out.println(order);
        phOrderMapper.updateOrderPrice(order);


    }

    public List<Order> getOrderListById() {
        String sessionId = (String) request.getSession().getAttribute("S_ID");
        return phOrderMapper.getOrderListById(sessionId);
    }
}
