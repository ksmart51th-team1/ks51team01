package team01.yaksutor.dto;

import lombok.Data;

@Data
public class Medicine {
    private String mediCode;
    private String regMId;
    private String mediName;
    private String mediImg;
    private String mediDrugform;
    private String mediDrugtype;
    private String mediState;
    private String mediUsebydate;
    private String regDate;

    private ImgData imgData;
}
