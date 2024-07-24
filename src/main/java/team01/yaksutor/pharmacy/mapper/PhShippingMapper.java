package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Shipping;

import java.util.List;

@Mapper
public interface PhShippingMapper {
    List<Shipping> getShipInfoByNum(String portNum);
}
