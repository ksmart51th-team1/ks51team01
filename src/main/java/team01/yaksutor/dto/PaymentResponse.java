package team01.yaksutor.dto;

public class PaymentResponse {
    private String impUid; // 아임포트 결제 고유 ID
    private String merchantUid; // 상점에서 생성한 고유 주문 ID
    private int paidAmount; // 결제 금액
    private String applyNum; // 카드 승인 번호
}
