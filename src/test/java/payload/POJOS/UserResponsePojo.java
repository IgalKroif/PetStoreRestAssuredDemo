package payload.POJOS;

import lombok.Data;

@Data
public class UserResponsePojo {
    private int code;
    private String type;
    private String message;
}
