package team01.yaksutor.dto;

import lombok.Data;

@Data
public class ShoppingCart {
    private String shoppingCartCode;
    private String goodsCode;
    private String oMId;
    private String qty;
    private String shoppingCartGroup;
    private String regDate;


    // sellMedicine
    private String mediName;
    private int mediPrice;
    private String mediDetail;

    // 총합
    private int sumQty;
    // 총합금액
    private int allPrice;

    // 이미지
    private String filePath;

}
