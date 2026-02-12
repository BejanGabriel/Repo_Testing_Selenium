package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

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

   @And("I remove ads")
   public void removeAds(){
        if(homePage.getLogoBanner().isDisplayed()){

        }
   }

    @Then("I research for \"{}\"")
    public void researchNew(String context){
    homePage.getCercaButton().click();
    homePage.getInputField().sendKeys(context);
    homePage.getSubmitResearcButton().click();
    }



}
