package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InterviewPage extends AbsBasePage {

    public InterviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Интервью']")
    WebElement interviewButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addInterviewButton;

    @FindBy(xpath = "//input[@class = 'form-control ']")
    WebElement interviewFieldName;

    @FindBy(xpath = "//input[@type='text']")
    WebElement interviewDateRedactField;

    @FindBy(xpath = "(//button[text()='Сохранить'])[1]")
    WebElement interviewSaveButton;

    @FindBy(xpath = "//tbody[1]/tr/td[1]")
    WebElement firstInterview;


    public void interviewDateRedact(String date){
        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewButton));
        actions.moveToElement(interviewButton).perform();
        interviewButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(firstInterview));
        firstInterview.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewDateRedactField));
        interviewDateRedactField.clear();
        interviewDateRedactField.sendKeys(date);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(interviewSaveButton));
        interviewSaveButton.click();
    }

    public void interviewCheckDate(String date) {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(interviewButton));
        actions.moveToElement(interviewButton).perform();
        interviewButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(firstInterview));
        firstInterview.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewDateRedactField));

        String actualDate = interviewDateRedactField.getAttribute("value");

        try {
            Assertions.assertEquals(date, actualDate,
                    "Ожидаемая дата не совпадает с фактической");
            System.out.println("Тест завершен успешно: дата совпадает (" + date + ")");
        } catch (AssertionError e) {
            takeScreenshot("InterviewDateMismatch.png");
            System.out.println("Тест провален! Ожидаемая дата: " + date +
                    ", но фактическая: " + actualDate);
            throw e;
        }
    }
}