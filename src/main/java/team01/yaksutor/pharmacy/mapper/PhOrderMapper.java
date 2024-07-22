package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Order;
import team01.yaksutor.dto.OrderDetail;
import team01.yaksutor.dto.SellerInfo;

import java.util.List;

@Mapper
public interface PhOrderMapper {
    SellerInfo getSellerInfoByGoodsCode(String goodsCode);

    void addOrder(Order order);

    void addOrderDetail(OrderDetail orderDetail);

    void updateOrderPrice(String oCode, int price);

    void updateOrderPrice(Order order);

    List<Order> getOrderListById(String sessionId);

    List<OrderDetail> getOrderDetailListByOCode(String oCode);

    void deleteOrderDetail(String orderDetailCode);

    OrderDetail getOrderDetailByCode(String orderDetailCode);
}
