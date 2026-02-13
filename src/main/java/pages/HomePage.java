package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;

public class HomePage extends Page{

    //passo il driver direttamente al padre, Page
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //ELEMENTI ADS
/*    @FindBy(xpath = "//*[contains(@id , 'adv]/a/img")
    private WebElement imgAds;*/
// xpath per btn dinamic: //button[contains(text(), 'Services') or contains(@id, 'Services')]
// dove 'Service' verrà sostituito da qualsiasi input

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
    private WebElement bottom;

    public WebElement getBottom() {
        return bottom;
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

    public void clickOnButton(String textButton){
        String xpath = "//button[contains(text(), '" + textButton + "') or contains(@id, '" + textButton + "') or contains(@class, '" + textButton + "')]";
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }

    @Override
    public void clickBackArrow() {
        driver.navigate().back();
    }

    @Override
    public void refreshPage() {
        driver.navigate().refresh();
    }

    @Override
    public void clickForwardArrow() {
        driver.navigate().forward();
    }

    @Override
    public void takeScreenshoot() {
        WebElement pagina = driver.findElement(By.tagName("html"));
        File scrFile = pagina.getScreenshotAs(OutputType.FILE);
        try {

            // modificare in modo da renderlo dinamico -> devo poter salvare la pic anche nel mio pc se facio il clone -> cambiare il path
            String projectPath = System.getProperty("user.dir");
            FileHandler.copy(scrFile, new File(projectPath + "\\screenshoot\\screnshoot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
