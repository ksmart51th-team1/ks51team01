package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Refund;

import java.util.List;

@Mapper
public interface PhRefundMapper {
    List<Refund> getRefundList(String sid);
}
