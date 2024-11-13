    package pages;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.ui.ExpectedConditions;

    import static org.junit.jupiter.api.Assertions.assertTrue;

    public class UsersPage extends AbsBasePage {

        public UsersPage(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = "//a[text()= 'Пользователи']")
        WebElement usersButton;

        @FindBy(xpath = "//input[@placeholder= 'Поиск ...']")
        WebElement searchField;

        @FindBy(xpath = "//th[text()='Логин']")
        WebElement loginTitle;


        public void searchUserByName(String userName) {
            waiter.waitForCondition(ExpectedConditions.elementToBeClickable(usersButton));
            actions.moveToElement(usersButton).perform();
            usersButton.click();
            waiter.waitForCondition(ExpectedConditions.visibilityOf(loginTitle));
            waiter.waitForCondition(ExpectedConditions.visibilityOf(searchField));
            searchField.sendKeys(userName);
        }

        public void verifyUserExists(String userName) {
            By firstSearchResult = By.xpath("//span[text()='" + userName + "']");

            try {
                assertTrue(
                        waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(firstSearchResult)),
                        "Пользователь с именем '" + userName + "' не найден."
                );
                System.out.println("Тест завершен успешно: пользователь с именем '" + userName + "' найден.");
            } catch (AssertionError e) {
                System.out.println("Тест провален! Пользователь с именем '" + userName + "' не найден.");
                throw e;
            }
        }
    }