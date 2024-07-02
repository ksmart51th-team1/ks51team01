package team01.yaksutor.pharmacy.dto;

import lombok.Data;

import java.util.List;

@Data
public class Member {
    private String memberId;
    private int memberLevelNum;
    private String memberLevelName;
    private String memberPw;
    private String memberName;
    private String memberGender;
    private String memberEmail;
    private String memberBirth;
    private String memberPhone;
    private String memberAddrSido;
    private String memberAddrSigungu;
    private String memberAddrDong;
    private String memberPostCode;
    private String memberAddr;

    private List<Level> memberLevelList;
}
