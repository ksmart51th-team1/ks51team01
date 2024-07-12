package team01.yaksutor.dto;

import lombok.Data;

@Data
public class LoginHistory {
    private int logNum;
    private String memberId;
    private String browser;
    private String loginDate;
    private String logoutDate;
}
