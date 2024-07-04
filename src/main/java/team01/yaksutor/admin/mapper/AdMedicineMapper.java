package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdMedicineMapper {

    int checkLevel(String regMId);
}
