package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.pharmacy.dto.Board;

import java.util.List;

@Mapper
public interface PhMedicineMapper {
    //  조회
    List<SellMedicine> getSellMedicineList();
}
