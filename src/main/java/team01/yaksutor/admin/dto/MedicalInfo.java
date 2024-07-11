package team01.yaksutor.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedicalInfo{
    private String mediCode;
    private String regMId;
    private String mediName;
    private String mediImg;
    private String mediDrugform;
    private String mediDrugtype;
    private String mediState;
    private String mediUsebydate;
    private String regDate;

    private AdImgData imgInfo;
    private List<AdIngredient> ingrList;
    private List<AdEfficacy> effiList;
}
