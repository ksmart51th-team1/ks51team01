package team01.yaksutor.dto;

import lombok.Data;

@Data
public class SellMedicine {
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
    private String filePath;
    private ImgData imgData;
    private String mediDrugForm;
    private String mediDrugType;
    private String mediState;
}
