package manager;

import dto.UserDTO;
import dto.UserDTOWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper{
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By btnOpenLoginForm = By.xpath("//a[@href='/login']");
    By inputEmail = By.xpath("//input[@name='email']");
    By inputPassword = By.xpath("//input[@name='password']");
    By btnLogin = By.xpath("//button[@name='login']");
    String btnRegistration = "document.querySelector('[name=\"registration\"]').click();\n";
    By textContacts = By.xpath("//a[@href='/contacts']");

    public void fillLoginUserDto(UserDTO userDTO) {
        clickBase(btnOpenLoginForm);
        typeTextBase(inputEmail, userDTO.getEmail());
        typeTextBase(inputPassword, userDTO.getPassword());
        clickBase(btnLogin);
    }

    public void fillLoginUserDtoWith(UserDTOWith userDTOWith) {
        clickBase(btnOpenLoginForm);
        typeTextBase(inputEmail, userDTOWith.getEmail());
        typeTextBase(inputPassword, userDTOWith.getPassword());
        clickBase(btnLogin);
    }

    public boolean validateContactTextDisplaysMainMenu() {
        String expectedResult = "CONTACTS".toUpperCase();
        return isTextEqual(textContacts, expectedResult);
    }
}
