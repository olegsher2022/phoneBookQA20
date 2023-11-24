package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.MessageResponseDTO;
import dto.NewContactDto;

public class ContactsService extends BaseApi{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;

    // ------------------------------------------ responseAddNewContact

    private Response getResponseAddNewContact(NewContactDto newContactDto, String token) {
        responseAddNewContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(newContactDto)
                .when()
                .post(baseUrl + "/v1/contacts");
        return responseAddNewContact;
    }

    public void setResponseAddNewContactNull() {
        responseAddNewContact = null;
    }

    public int getStatusCodeResponseAddNewContact(NewContactDto contactDto, String token) {
        if(responseAddNewContact == null) {
            responseAddNewContact = getResponseAddNewContact(contactDto, token);
        }
        return responseAddNewContact.getStatusCode();
    }

    public String getMessagePositiveResponseAddNewContact(NewContactDto contactDto, String token) {
        if(responseAddNewContact == null) {
            responseAddNewContact = getResponseAddNewContact(contactDto, token);
        }
        return responseAddNewContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    public String getIdResponseAddNewContact(NewContactDto contactDto, String token) {
        return getMessagePositiveResponseAddNewContact(contactDto, token).split(":")[1].trim();
    }

    //-----------------------------------------------------------------------------------------

    // ---------------------------------- responseDeleteOneContact

    private Response getResponseDeleteOneContact(String token, String id) {
         responseDeleteOneContact = RestAssured.given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/" + id);
         return responseDeleteOneContact;
    }

    public void setNullResponseDeleteOneContact() {
        responseDeleteOneContact = null;
    }

    public int getStatusCodeResponseDeleteOneContact(String token, String id) {
        if(responseDeleteOneContact == null) {
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);
        }
        return responseDeleteOneContact.getStatusCode();
    }

    public String getMessageDeleteOneContact(String token, String id) {
        if(responseDeleteOneContact == null) {
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);
        }
        return responseDeleteOneContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    // ------------------------------------------------------------
    // ------------------------------------------------- responseDeleteAllContacts
    private Response getResponseDeleteAllContacts(String token) {
        responseDeleteAllContacts = RestAssured.given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/clear");
        return responseDeleteAllContacts;
    }

    public void setResponseDeleteAllContactsNull() {
        responseDeleteAllContacts = null;
    }

    public int getStatusCodeResponseDeleteAllContacts(String token) {
        if(responseDeleteAllContacts == null) {
            responseDeleteAllContacts = getResponseDeleteAllContacts(token);
        }
        return responseDeleteAllContacts.getStatusCode();
    }

    public String getMessageResponseDeleteAllContactsPositive(String token) {
        if(responseDeleteAllContacts == null) {
            responseDeleteAllContacts = getResponseDeleteAllContacts(token);
        }
        return responseDeleteAllContacts.getBody().as(MessageResponseDTO.class).getMessage();
    }
}
