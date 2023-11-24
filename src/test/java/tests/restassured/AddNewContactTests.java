package tests.restassured;

import dto.NewContactDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

public class AddNewContactTests extends BaseRA{

    @Test
    public void StatusCodePositiveAddNewContact() {
        Assert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(createNewContact(), token), 200);
    }

    @Test
    public void messagePositiveAddContact() {
        System.out.println(contactsService.getMessagePositiveResponseAddNewContact(createNewContact(), token));
        Assert.assertTrue(contactsService.getMessagePositiveResponseAddNewContact(createNewContact(), token)
                .contains("Contact was added!"));
    }
}
