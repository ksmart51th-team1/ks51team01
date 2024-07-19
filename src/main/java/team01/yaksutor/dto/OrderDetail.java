package team01.yaksutor.dto;

import lombok.Data;

@Data
public class OrderDetail {

    private String orderDetailCode; // 주문 상세 코드
    private String oCode; // 주문 코드
    private String goodsCode; // 등록 의약품 코드
    private String oSellerId; //판매자Id
    private String suppCode; //납품업체코드
    private int orderQty; // 주문 수량
    private int orderPrice; // 구매 액수

    private SellMedicine sellMedicine;
    private ImgData imgData;
}
