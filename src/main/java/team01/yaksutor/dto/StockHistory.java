package team01.yaksutor.dto;

import lombok.Data;

@Data
public class StockHistory {
    private String stockHistoryCode;
    private String pharCode;
    private String mediCode;
    private int inoutQty;
    private int realInoutQty;
    private int orderInoutQty;
    private String inoutType;
    private int afterQty;
    private String inoutDate;

    private Medicine medicine;
}
