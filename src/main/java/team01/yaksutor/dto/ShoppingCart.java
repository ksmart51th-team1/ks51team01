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

}
