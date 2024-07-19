package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import team01.yaksutor.dto.PaymentResponse;
import team01.yaksutor.pharmacy.service.PhPaymentService;


@Controller
@RequiredArgsConstructor
public class PhPaymentController {

    private final PhPaymentService phPaymentService;

    @PostMapping("/pharm/paymentComplete")
    public ResponseEntity<Void> paymentComplete(@RequestBody PaymentResponse response) {
        try {
            phPaymentService.handlePaymentCompletion(response);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
