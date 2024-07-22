package team01.yaksutor.supplier.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team01.yaksutor.dto.Order;
import team01.yaksutor.supplier.mapper.SuOrderMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuOrderService {

    private final HttpServletRequest request;
    private final SuOrderMapper suOrderMapper;

    public List<Order> getOrderListById() {
        String sId = (String)request.getSession().getAttribute("S_ID");
        return suOrderMapper.getOrderListById(sId);
    }
}
