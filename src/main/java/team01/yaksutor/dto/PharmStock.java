package team01.yaksutor.dto;

import lombok.Data;

@Data
public class PharmStock {
    private String stockCode;
    private String mediCode;
    private String pharCode;
    private String pharmName;
    private String company;
    private int pharQty;
    private String manufactDate;
    private String expiraDate;
    private String stockChecked;
    private String lastDate;

    private Medicine medicine;
}
