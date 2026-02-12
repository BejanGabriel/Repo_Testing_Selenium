package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import pages.HomePage;

import java.io.File;
import java.io.IOException;

public class HomePageStepDefinition{

    private HomePage homePage;
    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @Given("Land on homepage \"{}\"")
    public void landOnHomepage(String urlHomepage){
        homePage.openPage(urlHomepage);

    }
   @Then("I remove cookies screen")
    public void removeCookies() {
       homePage.getAcceptCookieButton().click();
   }

   @And("I remove ads and take screenshot")
   public void removeAds(){
        if(!homePage.getLogoBanner().isDisplayed()){
            WebElement pagina = driver.findElement(By.tagName("html"));
            File scrFile = pagina.getScreenshotAs(OutputType.FILE);
            try {
                FileHandler.copy(scrFile, new File("C:\\Users\\Gabriel Bejan\\Desktop\\Esercizi\\Task_1\\screenshoot\\screnshoot.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            homePage.getButtonCloseAds().click();
        }else{
            System.out.println("No ADS was found!");
        }
   }

    @Then("I research for \"{}\"")
    public void researchNew(String context){
    homePage.getCercaButton().click();
    homePage.getInputField().click();
    homePage.getInputField().sendKeys(context);
    homePage.getSubmitResearcButton().click();
    }


    @And("I scroll to bottom")
    public void scrollToBottom(){
        new Actions(driver)
                .sendKeys(Keys.END)
                .perform();

/*        Scrolla fino alla fine della pagina (Funziona ovunque)
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");*/

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("I quit page")
    public void quitPage(){
        driver.quit();
    }



}
