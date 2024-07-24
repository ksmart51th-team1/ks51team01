package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.RefundDetail;
import team01.yaksutor.dto.Shipping;

import java.util.List;

@Mapper
public interface AdRefundMapper {
    List<Refund> getRefundList();

    List<Refund> getRefundDetailList(String refundCode);

    Shipping getShippingDetail(String refundCode);

    Refund getRefundTotal(String refundCode);

    String getPaymentMethod(String oCode);
}
