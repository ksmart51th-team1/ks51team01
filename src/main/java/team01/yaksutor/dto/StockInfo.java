package team01.yaksutor.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockInfo {
    private PharmStock pharmStock;
    private Medicine medicine;
    private List<Ingredient> ingrList;
    private List<Efficacy> effiList;
}
