package payload.POJOS;

import lombok.Data;

@Data
public class UserGenericErrorResponsePojo {
    private int code;
    private String type;
    private String message;
}
