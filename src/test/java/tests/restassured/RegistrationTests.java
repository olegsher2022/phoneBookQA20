package tests.restassured;

import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseRA{
    String emailR = randomUtils.generateEmail(7);

    UserDtoLombok userR = UserDtoLombok.builder()
            .username(emailR)
            .password("123456Aa$")
            .build();
    @Test
    public void testStatusCodePositiveRegistration() {
        System.out.println(emailR);
        System.out.println(userApi.getStatusCodeResponseRegistration(userR));
        Assert.assertTrue(userApi.getStatusCodeResponseRegistration(userR) == 200);
    }

    @Test
    public void testGetTokenPositiveRegistration(){
        System.out.println(userApi.getTokenRegistrationResponse(userR));
    }

    @Test
    public void NegativeRegistrationEmailExist() { // start this test only separate from others
        UserDtoLombok userN = UserDtoLombok.builder()
                .username("zn8uybj@domain.com")
                .password("123456Aa$")
                .build();
        userApi.responseRegistrationSetNull();
        int statusCode = userApi.getStatusCodeResponseRegistration(userN);
        userApi.responseRegistrationSetNull();
        Assert.assertEquals(statusCode, 409);
    }

}
