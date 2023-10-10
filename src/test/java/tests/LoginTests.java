package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {
    UserDTOWith userDTOWith = new UserDTOWith()
            .withEmail("testqa20@gmail.com")
            .withPassword("123456Aa$");
    UserDTO userDTO = new UserDTO("testqa20@gmail.com", "123456Aa$");

    @Test
    public void positiveLoginUserDto() {
        app.getUserHelper().fillLoginUserDto(userDTO);
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

    @Test
    public void positiveLoginUserDtoWith() {
        app.getUserHelper().fillLoginUserDtoWith(userDTOWith);
        Assert.assertTrue(app.getUserHelper().validateContactTextDisplaysMainMenu());
    }

}
