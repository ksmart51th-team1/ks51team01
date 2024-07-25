package team01.yaksutor.supplier.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.*;

import java.util.List;

@Mapper
public interface SuRefundMapper {
    Refund getRefundTotal(String refundCode);

    String getPaymentMethod(String oCode);

    List<Refund> getRefundDetailByRefundCode(String refundCode, String suppCode);

    String getSuppCodeById(String sid);

    List<Refund> getRefundListBySuppCode(String suppCode);

    Shipping getShippingDetail(String refundCode);

    void insertShipping(Shipping shipping);

    void updateShipping(Shipping shipping);

    void updateRefund(String refundCode);
}
