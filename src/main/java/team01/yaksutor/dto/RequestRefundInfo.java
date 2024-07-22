package team01.yaksutor.dto;

import lombok.Data;

@Data
public class RequestRefundInfo {
    private String orderCode;
    private String refundReason;
}
