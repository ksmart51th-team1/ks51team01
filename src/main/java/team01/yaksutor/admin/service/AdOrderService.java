package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team01.yaksutor.admin.mapper.AdOrderMapper;
import team01.yaksutor.dto.Order;
import team01.yaksutor.dto.OrderDetail;
import team01.yaksutor.pharmacy.mapper.PhOrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdOrderService {

    private final AdOrderMapper adOrderMapper;
    private final PhOrderMapper phOrderMapper;

    /**
     * 관리자 화면에서 전체 주문 목록 조회 서비스
     * @return List<Order>
     */
    public List<Order> getOrderList() {
        return adOrderMapper.getOrderList();
    }

    /**
     * 관리자 화면에서 특정 주문 삭제
     * @param oCode
     * @return
     */
    public boolean deleteOrderByoCode(String oCode) {
        try {
            //oCode로 orderDeatil 전체 조회
            List<OrderDetail> orderDetailList = phOrderMapper.getOrderDetailListByOCode(oCode);
            for (OrderDetail orderDetail : orderDetailList) {
                if(orderDetail.getOCode().equals(oCode)){
                    //주문상세 삭제
                    adOrderMapper.deleteOrderDetailById(oCode);
                }
            }
            //주문 삭제
            phOrderMapper.deleteOrder(oCode);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public List<Order> getOrderDetailListByoCode(String oCode) {
        return adOrderMapper.getOrderDetailsByoCode(oCode);
    }
}
