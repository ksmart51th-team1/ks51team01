package team01.yaksutor.admin.dto;

import lombok.Data;

@Data
public class SellMediInfo{
    private String goodsCode;
    private String suppCode;
    private String mediCode;
    private String mediName;
    private String mediDetail;
    private int mediPrice;
    private String mediImg;
    private int regQty;
    private String state;
    private String regDate;

    private AdImgData imgInfo;
}
