package team01.yaksutor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class PaymentResponse {

    private String impUid;
    private String merchantUid;
    private int paidAmount;
    private String applyNum;


}
