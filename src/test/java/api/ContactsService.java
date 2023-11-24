package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import dto.AllContactsDTO;
import dto.MessageResponseDTO;
import dto.ContactDto;

import static com.jayway.restassured.RestAssured.given;

public class ContactsService extends BaseApi{
    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;
    Response responseUpdateContact = null;
    Response responseGetAllContacts = null;

    // ------------------------------------------ responseAddNewContact

    private Response getResponseAddNewContact(ContactDto newContactDto, String token) {
        responseAddNewContact = given()
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
         responseDeleteOneContact = given()
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
        responseDeleteAllContacts = given()
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
        responseUpdateContact = given()
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
    //------------------------------- responseGetAllContacts
    private Response getResponseGetAllContacts(String token) {
        responseGetAllContacts = given()
                .header("Authorization", token)
                .when()
                .get(baseUrl + "/v1/contacts");
        return responseGetAllContacts;
    }

    public int getStatusCodeResponseGetAllContacts(String token) {
        if(responseGetAllContacts == null) {
            responseGetAllContacts = getResponseGetAllContacts(token);
        }
        return responseGetAllContacts.getStatusCode();
    }

    public boolean isIdInTheAllContactResponse(String token, String id) {
        if(responseGetAllContacts == null) {
            responseGetAllContacts = getResponseGetAllContacts(token);
        }
        ContactDto[] contacts = responseGetAllContacts.getBody().as(AllContactsDTO.class).getContacts();
        boolean flag = false;
        for(int i = 0; i < contacts.length; i++) {
            if(contacts[i].getId().equals(id)) {
                System.out.println("id from isID... " + contacts[i].getId());
                flag = true;
                return true;
            }
        }
        return flag;
    }
}
