package utilities.dataprovider;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.testng.annotations.DataProvider;

@NoArgsConstructor
public class DataProviders {
    @DataProvider(name = "differentIdFieldsProvider")
    public static Object[][] provideIdUserData() {
        Faker faker = new Faker();
        return new Object[][]{
                {
                        123456789,
                        faker.name().username(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        faker.phoneNumber().toString(),
                        faker.number().randomDigit()
                },
                {
                        987654321,
                        "stan.brown",
                        "Daine",
                        "Effertz",
                        "star.quitzon@yahoo.com",
                        "nbkw12tb",
                        "216.821.3715 x3009",
                        5
                },
                {
                        0,
                        "stan.brown",
                        "Daine",
                        "Effertz",
                        "star.quitzon@yahoo.com",
                        "nbkw12tb",
                        "216.821.3715 x3009",
                        5
                }
                // ... you can add more sets of data here
        };
    }


    @DataProvider(name = "differentUserNameFieldsProvider")
    public static Object[][] provideUserNameData() {
        Faker faker = new Faker();
        return new Object[][]
        {
                {
                        123456789,
                        "stan.brown",
                        "Daine",
                        "Effertz",
                        "star.quitzon@yahoo.com",
                        "nbkw12tb",
                        "216.821.3715 x3009",
                        5
                }
        };
    }
}