package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.RefundDetail;
import team01.yaksutor.dto.Shipping;

import java.util.List;

@Mapper
public interface AdRefundMapper {
    // 반품 목록 검색
    List<Refund> getRefundList();

    // 반품 상세 정보 검색
    List<Refund> getRefundDetailList(String refundCode);

    // 배송 상세 검색
    Shipping getShippingDetail(String refundCode);

    // 총 반품 금액 검색
    Refund getRefundTotal(String refundCode);

    // 주문 시 결제방식 검색
    String getPaymentMethod(String oCode);

    // 반품 배송 등록
    void insertShipping(Shipping shipping);

    // 배송 상태 수정
    void updateShipping(Shipping shipping);

    // 반품 상태 수정
    void updateRefund(String refundCode);
}
