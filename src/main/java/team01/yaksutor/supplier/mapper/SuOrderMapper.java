package team01.yaksutor.supplier.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Order;

import java.util.List;

@Mapper
public interface SuOrderMapper {

    //판매자 회원의 주문리스트 조회
    List<Order> getOrderListById(String sId);

    //판매자 ID로 주문상세 삭제
    void deleteOrderDetailById(String sId,String oCode);

    List<Order> getOrderDetailsBySellerIdAndOrderCode(String oCode, String sId);
}
