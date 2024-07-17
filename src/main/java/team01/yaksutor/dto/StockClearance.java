package team01.yaksutor.dto;

import lombok.Data;

@Data
public class StockClearance {
    private String stockClearCode;
    private String stockCode;
    private int preInveQty;
    private int postInveQty;
    private int adnormalQty;
    private String adnormalChecked;
    private String adnormalReason;
    private String regDate;

    private String mediName;
}
