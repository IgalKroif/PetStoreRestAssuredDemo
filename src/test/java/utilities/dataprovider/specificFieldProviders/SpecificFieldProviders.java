package utilities.dataprovider.specificFieldProviders;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class SpecificFieldProviders {
    @DataProvider(name = "differentIdFields")
    public static Object[][] singleTonTests() {
        Faker faker = new Faker();
        return new Object[][]
                {
                        {"1"},
                        {"10"},
                        {"aaaaaa"},
                        {12345678912345678912D},
                        {1.500F},
                        {""},
                        {null},
                        {true},
                        {false}
                };
    }
}
