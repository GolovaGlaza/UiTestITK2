package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdditionalQuestionsPage extends AbsBasePage{

    public AdditionalQuestionsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Доп. вопросы']")
    WebElement additionalQuestionButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addButton;

    @FindBy(xpath = "(//textarea)[1]")
    WebElement questionField;

    @FindBy(xpath = "(//textarea)[2]")
    WebElement clarifyingQuestionField;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement createButton;

    @FindBy(xpath = "//tbody/tr/td[1]/a[1]")
    WebElement questionName;


    public void createNewQuestion(String question, String clarifyingQuestion){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(additionalQuestionButton));
        actions.moveToElement(additionalQuestionButton).perform();
        additionalQuestionButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(questionField));
        actions.moveToElement(questionField).perform();
        questionField.sendKeys(question);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(clarifyingQuestionField));
        clarifyingQuestionField.sendKeys(clarifyingQuestion);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(createButton));
        actions.moveToElement(createButton).perform();
        createButton.click();
    }

    public void verifyNewQuestion(String question){
        waiter.waitForCondition(ExpectedConditions.visibilityOf(questionName));
        String lastQuestionName = questionName.getText();

        try {
            Assertions.assertEquals(question, lastQuestionName,
                    "Вопрос с именем '" + question + "' не создан.");
            System.out.println("Тест завершен успешно: вопрос с именем '" + question + "' создан.");
        }catch (AssertionError e) {
            System.out.println("Тест провален.");
            throw e;
        }
    }
}
