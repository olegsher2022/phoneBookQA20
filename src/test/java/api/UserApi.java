package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AuthResponseDTO;
import dto.UserDtoLombok;

import static com.jayway.restassured.RestAssured.given;

public class UserApi extends BaseApi {

    Response responseLogin = null;
    Response responseRegistration = null;

    // ------------------------------------- responseLogin
    private Response loginRequest(UserDtoLombok user) {
        System.out.println("----------------------- loginRequest method run");
        responseLogin = given()
//                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(baseUrl + "/v1/user/login/usernamepassword")
                .thenReturn();
        return responseLogin;
    }

    public void setResponseLoginNull() {
        responseLogin = null;
    }

    public int getStatusCodeResponseLogin(UserDtoLombok user) {
        if(responseLogin == null) {
            responseLogin = loginRequest(user);
        }
        return responseLogin.getStatusCode();
    }

    public String getTokenFromLoginResponse(UserDtoLombok user) {
        if(responseLogin == null) {
            responseLogin = loginRequest(user);
        }
        return responseLogin.getBody().as(AuthResponseDTO.class).getToken();
    }

    // ---------------------------- responseRegistration

    private Response getRegistrationResponse(UserDtoLombok user) {
        responseRegistration = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(baseUrl + "/v1/user/registration/usernamepassword")
                .thenReturn();
        return responseRegistration;
    }

    public void responseRegistrationSetNull() {
        responseRegistration = null;
    }

    public int getStatusCodeResponseRegistration(UserDtoLombok user) {
        if(responseRegistration == null) {
            responseRegistration = getRegistrationResponse(user);
        }
        return responseRegistration.getStatusCode();
    }

    public String getTokenRegistrationResponse(UserDtoLombok user) {
        if(responseRegistration == null) {
            responseRegistration = getRegistrationResponse(user);
        }
        return responseRegistration.then().extract().path("token");
    }

}
