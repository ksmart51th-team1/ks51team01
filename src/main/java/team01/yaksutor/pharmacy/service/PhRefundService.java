package team01.yaksutor.pharmacy.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.controller.AdRefundController;
import team01.yaksutor.dto.OrderDetailForRefund;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.RefundDetail;
import team01.yaksutor.dto.RequestRefundInfo;
import team01.yaksutor.pharmacy.mapper.PhRefundMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PhRefundService {
    private final PhRefundMapper phRefundMapper;
    private final HttpServletRequest request;
    private final AdRefundController adRefundController;

    @Transactional
    public void insertRefund(RequestRefundInfo rri){
        Refund refund = new Refund();
        String oCode = rri.getOrderCode();
        List<OrderDetailForRefund> odList = phRefundMapper.getRefundOrderList(oCode);
        int totalPrice = 0;
        for(OrderDetailForRefund od : odList){
            totalPrice += od.getOrderPrice();
        }
        String pharId = phRefundMapper.getPharId(oCode);
        refund.setPharId(pharId);
        refund.setOCode(oCode);
        refund.setRefundTotalMoney(totalPrice);
        String reason = rri.getRefundReason();
        refund.setRefundReason(reason);
        String state = "반품 처리중";
        refund.setRefundState(state);
        log.info("refund: {}", refund);
        phRefundMapper.insertRefund(refund);
        log.info("refund: {}", refund);
        for(OrderDetailForRefund od : odList){
            RefundDetail rd = new RefundDetail();
            rd.setRefundCode(refund.getRefundCode());
            String goodsCode = od.getGoodsCode();
            String mediCode = phRefundMapper.getMediCodeByGoodsCode(goodsCode);
            rd.setMediCode(mediCode);
            rd.setRefundQty(od.getOrderQty());
            rd.setRefundMoney(od.getOrderPrice());
            rd.setSuppCode(od.getSuppCode());
            log.info("refundDetail: {}", rd);
            phRefundMapper.insertRefundDetail(rd);
        }

    }

}

