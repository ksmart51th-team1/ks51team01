package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.PaymentResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhPaymentService {

    @Transactional
    public void handlePaymentCompletion(PaymentResponse response) {
        // 결제 완료 처리 로직
        // 예시:
        // Order order = phOrderMapper.getOrderByMerchantUid(response.getMerchantUid());
        // order.setPaidAmount(response.getPaidAmount());
        // order.setApplyNum(response.getApplyNum());
        // order.setStatus("PAID");
        // phOrderMapper.updateOrder(order);
    }
}
