package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.SellerInfo;

@Mapper
public interface PhOrderMapper {
    SellerInfo getSellerInfoByGoodsCode(String goodsCode);
}
