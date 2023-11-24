package tests;

import dto.ContactDto;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteOneContactTestWithApi extends BaseTests{

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {
        if(app.isPageUrlHome()) {
            app.getUserHelper().openLoginPage();
        }
        app.getUserHelper().fillLoginUserDtoLombok(user);
    }

    @AfterClass(alwaysRun = true)
    public void postConditions() throws InterruptedException {
        app.getUserHelper().logout();
        Thread.sleep(500);
    }

    @Test
    public void deleteCreatedContactPositive() throws InterruptedException {
        String phone = randomUtils.generateStringDigits(12);
        System.out.println("phone for the new contact: " + phone);
        logger.info("phone for the new contact: " + phone);
        ContactDto newContactDto = ContactDto.builder()
                .address("ghj")
                .description("asadhj")
                .email("dfrdd@mail.ff")
                .lastName("ghjlkd")
                .name("dsbj")
                .phone(phone)
                .build();
   softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(newContactDto, token), 200);
      //  app.getContactHelper().addNewContact(newContactDto);
        app.getContactHelper().openContactInfoByPhone(phone);
        Thread.sleep(5000);
        app.getContactHelper().removeActiveContact();
        app.getContactHelper().navigateToContactPage();
        System.out.println(phone);
        Thread.sleep(5000);
        softAssert.assertFalse(app.getContactHelper().validateContactCreated(phone));
        softAssert.assertAll();
    }
}
