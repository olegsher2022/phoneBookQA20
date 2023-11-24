package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.MessageResponseDTO;
import dto.ContactDto;

public class ContactsService extends BaseApi{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;
    Response responseUpdateContact = null;

    // ------------------------------------------ responseAddNewContact

    private Response getResponseAddNewContact(ContactDto newContactDto, String token) {
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

    public int getStatusCodeResponseAddNewContact(ContactDto contactDto, String token) {
        if(responseAddNewContact == null) {
            responseAddNewContact = getResponseAddNewContact(contactDto, token);
        }
        return responseAddNewContact.getStatusCode();
    }

    public String getMessagePositiveResponseAddNewContact(ContactDto contactDto, String token) {
        if(responseAddNewContact == null) {
            responseAddNewContact = getResponseAddNewContact(contactDto, token);
        }
        return responseAddNewContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    public String getIdResponseAddNewContact(ContactDto contactDto, String token) {
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

    // ---------------------- responseUpdateContact
    private Response getResponseUpdateContact(ContactDto newContactDto, String token) {
        responseUpdateContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(newContactDto)
                .when()
                .put(baseUrl + "/v1/contacts");
        return responseUpdateContact;
    }

    public int getStatusCodeResponseUpdateContact(ContactDto newContactDto, String token) {
        if(responseUpdateContact == null) {
            responseUpdateContact = getResponseUpdateContact(newContactDto, token);
        }
        return responseUpdateContact.getStatusCode();
    }

    public String getMessageSuccessUpdateContact(ContactDto newContactDto, String token) {
        if(responseUpdateContact == null) {
            responseUpdateContact = getResponseUpdateContact(newContactDto, token);
        }
        return responseUpdateContact.then().extract().path("message");
    }
}
