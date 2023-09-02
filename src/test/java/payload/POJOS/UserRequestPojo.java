package payload.POJOS;

import lombok.Data;
import lombok.Setter;

@Data
public class UserRequestPojo {
    public Object id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
