package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod() {
        preconditionForLoginAndRegTests();
    }
    UserDTOWith userDTOWith = new UserDTOWith()
            .withEmail("testqa20@gmail.com")
            .withPassword("123456Aa$");
    UserDTO userDTO = new UserDTO("testqa20@gmail.com", "123456Aa$");

    @Test(groups={"smoke"})
    public void positiveLoginUserDto() {
        logger.info("logger info: start test positiveLoginUserDto");
        logger.info(String
                .format("in the next function: fill email input with email: %s and with the password: %s and click on button login",
                        userDTO.getEmail(), userDTO.getPassword()));
        app.getUserHelper().fillLoginUserDto(userDTO);
        logger.info("validation by assertTrue, that contacts link displays in the menu");
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void positiveLoginUserDtoWith() {
        app.getUserHelper().fillLoginUserDtoWith(userDTOWith);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test(groups={"regression"})
    public void positiveLoginUserDtoLombok() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aa$")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test (groups = {"smoke"})
    public void negativeWrongPasswordWrongSymbol() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aa5")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

    @Test
    public void negativeWrongPasswordNoLetters() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("12345655$")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

    @Test
    public void negativeWrongPasswordNoDigits() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("ajdsbH#$dmk")
                .build();
        app.getUserHelper().fillLoginUserDtoLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailPasswordCorrect()); // Wrong email or password
    }

}
