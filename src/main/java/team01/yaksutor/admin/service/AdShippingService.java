package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.mapper.AdShippingMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdShippingService {
    private final AdShippingMapper adShippingMapper;

    /**
     * 반품 취소 트랜젝션
     * 작성자: 길범진
     * @param refundCode
     */
    @Transactional
    public void cancleRefund(String refundCode){
        String oCode = adShippingMapper.getOCodeByRefundCode(refundCode);

        adShippingMapper.deleteShipping(refundCode);
        adShippingMapper.deleteRefundDetail(refundCode);
        adShippingMapper.deleteRefund(refundCode);
        adShippingMapper.updateCancleRefund(oCode);
    }
}
