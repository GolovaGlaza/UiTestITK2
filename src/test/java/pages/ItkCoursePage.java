package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ItkCoursePage extends AbsBasePage{

    public ItkCoursePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath ="//a[@href= '/courses']" )
    WebElement courseButton;

    @FindBy(xpath = "//a[@class= 'mainCont3 coursePreview']")
    private List<WebElement> courseCards;


    public void checkCourseCards(int expectedCardCount){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(courseButton));
        actions.moveToElement(courseButton).perform();
        courseButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOfAllElements(courseCards));
        int courseCardsSize = courseCards.size();

        try {
            Assertions.assertEquals(expectedCardCount, courseCardsSize,
                    "Количество карточек не совпадает. Ожидаемое количество: "
                            + expectedCardCount + ", фактическое количество: " + courseCardsSize);
            System.out.println("Тест завершен успешно. Ожидаемое количество: "
                    + expectedCardCount + ", фактическое количество: " + courseCardsSize);
        }catch (AssertionError e){
            System.out.println("Тест провален. Ожидаемое количество: "
                    + expectedCardCount + ", фактическое количество: " + courseCardsSize);
            throw e;
        }
    }
}
