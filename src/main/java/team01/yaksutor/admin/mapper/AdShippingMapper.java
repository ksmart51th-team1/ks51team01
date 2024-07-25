package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Shipping;

import java.util.List;

@Mapper
public interface AdShippingMapper {
    // 주문 배송 목록
    List<Shipping> getOrderShipList();

    // 반품 배송 목록
    List<Shipping> getRefundShipList();

    // shipping 삭제
    void deleteShipping(String refundCode);

    // refundDetail 삭제
    void deleteRefundDetail(String refundCode);

    // refund 삭제
    void deleteRefund(String refundCode);

    // order 상태 반품 -> 결제완료
    void updateCancleRefund(String oCode);

    // 반품 코드로 주문 코드 검색
    String getOCodeByRefundCode(String refundCode);
}
