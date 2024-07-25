package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Order;

import java.util.List;

@Mapper
public interface AdOrderMapper {

    //전체 주문 목록 조회
    List<Order> getOrderList();
    //주문코드로 주문상세 정보 삭제
    void deleteOrderDetailById(String oCode);

    List<Order> getOrderDetailsByoCode(String oCode);
}
