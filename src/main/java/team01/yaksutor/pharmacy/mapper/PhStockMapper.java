package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.PharmStock;

import java.util.List;

@Mapper
public interface PhStockMapper {
    List<PharmStock> getPharmStockList();
}
