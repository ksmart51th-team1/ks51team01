package team01.yaksutor.pharmacy.dto;

import lombok.Data;

@Data
public class QnaReply {
    private String qrSeq;
    private String qseq;
    private String responder;
    private String title;
    private String content;
    private String outdate;
}
