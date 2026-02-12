package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page{

    //passo il driver direttamente al padre, Page
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h1[contains(@class, 'logo')]/a[@title = 'La Stampa']")
    private WebElement logoBanner;

    @FindBy(xpath = "//img[@id = 'closeButton']")
    private WebElement buttonCloseAds;

    @FindBy(xpath = "//button[text() = 'Accetta']")
    private WebElement acceptCookieButton;

    @FindBy(xpath = "(//button/span[text() = 'Cerca']/ancestor::button)[2]")
    private WebElement cercaButton;

    @FindBy(xpath = "//form[1]//div[1]//input[@type='search']")
    private WebElement inputField;

    @FindBy(xpath = "//form[@method='get']//button[@type='submit']")
    private WebElement submitResearcButton;

    @FindBy(tagName = "footer")
    private WebElement botoom;

    public WebElement getBotoom() {
        return botoom;
    }

    public WebElement getButtonCloseAds() {
        return buttonCloseAds;
    }

    public WebElement getAcceptCookieButton() {
        return acceptCookieButton;
    }

    public WebElement getLogoBanner() {
        return logoBanner;
    }

    public WebElement getInputField() {
        return inputField;
    }

    public WebElement getCercaButton() {
        return cercaButton;
    }

    public WebElement getSubmitResearcButton() {
        return submitResearcButton;
    }


}
