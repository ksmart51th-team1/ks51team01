package team01.yaksutor.dto;

import lombok.Data;

@Data
public class Response {
    private String status;
    private String message;

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
