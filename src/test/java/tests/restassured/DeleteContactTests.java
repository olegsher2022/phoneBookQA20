package tests.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactTests extends BaseRA{

    @Test
    public void deleteContactTest() {
        contactsService.setResponseAddNewContactNull();
        String id = contactsService.getIdResponseAddNewContact(createNewContact(), token);
        contactsService.setResponseAddNewContactNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteOneContact(token, id), 200);
        System.out.println(contactsService.getMessageDeleteOneContact(token, id));
        softAssert.assertEquals(contactsService.getMessageDeleteOneContact(token, id), "Contact was deleted!");
        contactsService.setNullResponseDeleteOneContact();
        softAssert.assertAll();
    }

    @Test
    public void deleteAllContactsWhenSMTHExist() {
        contactsService.getIdResponseAddNewContact(createNewContact(), token);
        contactsService.setResponseDeleteAllContactsNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);
        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token),
                "All contacts was deleted!");
        contactsService.setResponseDeleteAllContactsNull();
        softAssert.assertAll();
    }

    @Test
    public void deleteAllContactsWhenNoContacts() {
        contactsService.getStatusCodeResponseDeleteAllContacts(token);
        contactsService.setResponseDeleteAllContactsNull();
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);
        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token),
                "All contacts was deleted!");
        softAssert.assertAll();
    }
}
