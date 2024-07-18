package team01.yaksutor.dto;

import lombok.Data;

@Data
public class RefundDetail {
    private String refundDetailCode;
    private String refundCode;
    private String mediCode;
    private int refundQty;
    private int refundMoney;
    private String suppCode;
}
