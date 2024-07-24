package team01.yaksutor.supplier.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Shipping;

import java.util.List;

@Mapper
public interface SuShippingMapper {
    String getSuppCode(String sid);

    List<Shipping> getShippingList(String suppCode);

    List<Shipping> getRefundShippingList(String suppCode);
}
