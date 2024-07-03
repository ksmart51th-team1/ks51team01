package team01.yaksutor.pharmacy.dto;

import lombok.Data;

@Data
public class Notice {
    private String noticeNum;
    private String mId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeView;
    private String noticeDate;
}
