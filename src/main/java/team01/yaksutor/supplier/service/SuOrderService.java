package team01.yaksutor.supplier.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.Order;
import team01.yaksutor.dto.OrderDetail;
import team01.yaksutor.pharmacy.mapper.PhOrderMapper;
import team01.yaksutor.supplier.mapper.SuOrderMapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuOrderService {

    private final HttpServletRequest request;
    private final SuOrderMapper suOrderMapper;
    private final PhOrderMapper phOrderMapper;

    public List<Order> getOrderListById() {
        String sId = (String) request.getSession().getAttribute("S_ID");
        return suOrderMapper.getOrderListById(sId);
    }

    @Transactional
    public boolean deleteOrderByoCode(String oCode) {
        try{
            String sId = (String)request.getSession().getAttribute("S_ID");
            //oCode로 orderDeatil 전체 조회
            List<OrderDetail> orderDetailList = phOrderMapper.getOrderDetailListByOCode(oCode);

            //현재 로그인한 납품업체의 주문상세만 삭제
            for(OrderDetail orderDetail : orderDetailList) {
                if(orderDetail.getOSellerId().equals(sId)){
                    suOrderMapper.deleteOrderDetailById(sId,oCode);
                }
            }

            // 해당 주문 코드(oCode)로 남아 있는 OrderDetail 목록을 조회
            List<OrderDetail> remainingOrderDetails = phOrderMapper.getOrderDetailListByOCode(oCode);

            // 남아 있는 OrderDetail의 총 금액을 다시 계산
            int totalPrice = remainingOrderDetails.stream()
                    .mapToInt(OrderDetail::getOrderPrice)
                    .sum();

            //해당 주문코드에 새로운 값 셋팅
            Order order = new Order();
            order.setOCode(oCode);
            order.setOrderTotalPrice(totalPrice);

            // Order 테이블의 총 주문 금액을 업데이트
            phOrderMapper.updateOrderPrice(order);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<Order> getOrderDetailByOderCode(String oCode) {
        String sId = (String)request.getSession().getAttribute("S_ID");

        return suOrderMapper.getOrderDetailsBySellerIdAndOrderCode(oCode,sId);

    }
}
