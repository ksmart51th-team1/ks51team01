package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.ShoppingCart;

import java.util.List;

@Mapper
public interface PhShoppingCartMapper {

    // 조회
    List<ShoppingCart> getShoppingCartList();
    // 추가
    int addShoppingCart(ShoppingCart shoppingCart);
    // 삭제
    int deleteShoppingCart(String goodsCode, String oMID);

}
