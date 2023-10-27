package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper{


    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    By btnAddNewContact = By.xpath("//a[@href='/add']");
    By inputNameAddContact = By.xpath("//input[@placeholder='Name']");
    By inputLastNameAddContact = By.xpath("//input[@placeholder='Last Name']");
    By inputPhoneAddContact = By.xpath("//input[@placeholder='Phone']");
    By inputEmailAddContact = By.xpath("//input[@placeholder='email']");
    By inputAddressAddContact = By.xpath("//input[@placeholder='Address']");
    By inputDescriptionAddContact = By.xpath("//input[@placeholder='description']");
    By btnSaveNewContact = By.xpath("//button/b");

    /*
    click btnAddNewContact
    fill pcaceholder inputs 6
    btnSaveNewContact click
     */
}
