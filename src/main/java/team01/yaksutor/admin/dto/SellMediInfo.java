package team01.yaksutor.admin.dto;

import lombok.Data;

@Data
public class SellMediInfo extends AdSellMedicine{
    private String suppCode;
    private String mediCode;
    private String mediName;
    private String mediDetail;
    private int mediPrice;
    private String mediImg;
    private int regQty;
}
