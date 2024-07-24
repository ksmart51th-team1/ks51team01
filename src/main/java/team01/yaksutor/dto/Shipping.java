package team01.yaksutor.dto;

import lombok.Data;

@Data
public class Shipping {
    private String shipCode;
    private String oCode;
    private String refundCode;
    private String returnOrderStatus;
    private String deliveryState;
    private String transportNum;
    private String manager;
    private String deliveryLocation;
    private String deliDate;
    private String arrivalDate;

    private Order order;
    private Refund refund;
}
