package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team01.yaksutor.dto.PaymentResponse;
import team01.yaksutor.dto.Response;
import team01.yaksutor.pharmacy.service.PhOrderService;
import team01.yaksutor.pharmacy.service.PhPaymentService;


@RestController
@RequiredArgsConstructor
public class PhPaymentController {

    private final PhOrderService phOrderService;

    @PostMapping("/pharm/paymentComplete")
    @ResponseBody
    public ResponseEntity<?> paymentComplete(@RequestBody PaymentResponse paymentResponse) {
        try {
            phOrderService.updateOrderStatus(paymentResponse.getMerchantUid(), "결제완료");
            return ResponseEntity.ok().body(new Response("success", "결제가 정상적으로 완료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("error", "결제 처리 중 오류가 발생했습니다."));
        }
    }
}
