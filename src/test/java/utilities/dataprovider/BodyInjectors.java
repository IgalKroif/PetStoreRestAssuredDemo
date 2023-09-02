package utilities.dataprovider;

import org.testng.annotations.Test;
import payload.POJOS.UserRequestPojo;

public class BodyInjectors {
    static UserRequestPojo createUserRequest = new UserRequestPojo();

    public static Object validateIdField(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        // Create a custom body using the provided data
        // UserRequestPojo userRequest = new UserRequestPojo();
        createUserRequest.setId(id);
        createUserRequest.setUsername(username);
        createUserRequest.setFirstName(firstName);
        createUserRequest.setLastName(lastName);
        createUserRequest.setEmail(email);
        createUserRequest.setPassword(password);
        createUserRequest.setPhone(phone);
        createUserRequest.setUserStatus(userStatus);

        return createUserRequest;
    }
    //@Test(dataProvider = "differentUserNameFieldsProvider", dataProviderClass = DataProviders.class)
    public static Object validateUserNameField(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {

        createUserRequest.setId(id);
        createUserRequest.setUsername(username);
        createUserRequest.setFirstName(firstName);
        createUserRequest.setLastName(lastName);
        createUserRequest.setEmail(email);
        createUserRequest.setPassword(password);
        createUserRequest.setPhone(phone);
        createUserRequest.setUserStatus(userStatus);

        return createUserRequest;

    }
}