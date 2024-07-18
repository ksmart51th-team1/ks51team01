package team01.yaksutor.dto;

import lombok.Data;

import java.util.List;

@Data
public class Refund {
    private String refundCode;
    private String PharId;
    private String oCode;
    private int refundTotalMoney;
    private String refundReason;
    private String refundState;
    private String refundDate;

    private RefundDetail RDList;
    private Medicine mediList;
    private ImgData imgList;
    private Supplier suppList;
}
