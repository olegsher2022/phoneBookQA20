package tests.restassured;

import api.ContactsService;
import api.UserApi;
import dto.UserDtoLombok;
import org.testng.annotations.BeforeSuite;

public class BaseRA {
    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
    String token = "";

    UserDtoLombok user = UserDtoLombok.builder()
            .username("testqa20@gmail.com")
            .password("123456Aa$")
            .build();

    @BeforeSuite
    public void preCondApiRA() {
        token = userApi.getTokenFromLoginResponse(user);
    }
}
