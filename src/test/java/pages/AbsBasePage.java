package pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AbsCommon;

import java.io.File;
import java.io.IOException;

public abstract class AbsBasePage extends AbsCommon {
    String baseUrl;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        this.baseUrl = System.getProperty("base.url");
    }

    public void setBaseUrl(String environment){
        switch (environment.toUpperCase()){
            case "ITK_TEST":
                this.baseUrl = "https://razvitie.itk.academy/";
                break;
            default:
                this.baseUrl = System.getProperty("base.url");
                break;
        }
    }

    public void open(String path) {
        path = !path.startsWith("/") ? "/" + path : path;
        driver.get(baseUrl + path);

    }

    @FindBy(id = "username")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()= 'Вход']")
    WebElement authButton;

    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    public void login(){
        waiter.waitForCondition(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(username);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(authButton));
        authButton.click();
    }

    public void takeScreenshot(String fileName) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileHandler.copy(screenshot, new File(fileName));
                System.out.println("Скриншот сохранен: " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при сохранении скриншота: " + e.getMessage());
            }
        }
    }
}