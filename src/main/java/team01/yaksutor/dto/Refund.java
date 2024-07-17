package team01.yaksutor.dto;

import lombok.Data;

import java.util.List;

@Data
public class Refund {
    private String refundCode;
    private String PharId;
    private String SuppId;
    private String oCode;
    private int refundMoney;
    private String refundReason;
    private String refundState;
    private String refundDate;

    List<RefundDetail> RDList;
}
