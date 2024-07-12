package team01.yaksutor.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedicineInfo {
    private Medicine medicine;
    private SellMedicine sellMedicine;
    private List<Ingredient> ingrList;
    private List<Efficacy> effiList;
}
