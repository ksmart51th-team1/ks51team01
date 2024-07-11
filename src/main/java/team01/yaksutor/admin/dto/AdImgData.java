package team01.yaksutor.admin.dto;

import lombok.Data;

@Data
public class AdImgData {
    private String fileIdx;
    private String fileOriginName;
    private String fileNewName;
    private String filePath;
    private int fileSize;
    private String fileRegDate;
}
