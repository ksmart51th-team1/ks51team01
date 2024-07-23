package team01.yaksutor.dto;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String oCode; // 주문 코드
    private String mIdOrder; // 회원 아이디 (주문자)
    private String shoppingCartGroup; // 장바구니 그룹
    private int orderTotalPrice; // 구매 총액
    private String deliveryState; // 배송 상태
    private String purchaseState; // 구매 상태
    private String paymentMethod; // 결제 방식
    private String requestTerm; // 요청 사항
    private String orderType; // 거래 타입
    private String purchaseDate; // 구매 일자

    private OrderDetail orderDetail;
    private SellMedicine sellMedicine;
    private ImgData imgData;

}
