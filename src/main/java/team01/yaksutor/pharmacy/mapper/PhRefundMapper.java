package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.*;

import java.util.List;

@Mapper
public interface PhRefundMapper {
    List<Refund> getRefundList(String sid);

    List<Refund> getRefundDetailList(String refundCode);

    Refund getRefundInfo(String refundCode);

    List<OrderDetailForRefund> getOrderDetailList(String oCode);

    Order getOrderByOCode(String oCode);

    List<OrderDetailForRefund> getRefundOrderList(String oCode);

    String getPharId(String oCode);

    void insertRefund(Refund refund);

    String getMediCodeByGoodsCode(String goodsCode);

    void insertRefundDetail(RefundDetail refundDetail);

    void updateOrderState(Order order);
}
