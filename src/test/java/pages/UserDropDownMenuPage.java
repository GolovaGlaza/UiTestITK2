package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDropDownMenuPage extends AbsBasePage {

    public UserDropDownMenuPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@class= 'menuProfile']")
    WebElement dropDownMenu;

    @FindBy(xpath = "//li[contains(text(), 'Выход')]")
    WebElement logOutButton;

    @FindBy(xpath = "//form[@class= 'login-form' ]")
    WebElement loginForm;

    public void logOut(){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(dropDownMenu));
        actions.moveToElement(dropDownMenu).perform();
        dropDownMenu.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(logOutButton));
        logOutButton.click();
    }

    public void logOutCheck(){
        try{
            assertTrue(
                    waiter.waitForCondition(ExpectedConditions.visibilityOf(loginForm)),
                    "Пользователь не вышел из портала."
            );
            System.out.println("Тест завершен успешно: пользователь вышел из портала");
        }catch (AssertionError e) {
            System.out.println("Тест провален");
            throw e;
        }
    }
}
