package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePageStepDefinition {

    private HomePage homePage;
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @Given("Land on homepage")
    public void landOnHomepage() {

        // File.separator = inserisce il separatore usato dal sistema. ES: Win -> '\\'    Mac -> '//'

        String link = leggiFile().toString();
        System.out.println("Il link restituito dal metodo è: " + link);
        String regex = "^(ht|f)tp(s?)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(:[0-9]+)?(/.*)?$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        boolean matchFound = matcher.find();
        System.out.println(matchFound);
        if(matchFound){
            System.out.println("Il link è valido!");
            homePage.openPage(link);
        }else{
        System.out.println("Il Link del file 'sito.txt' non è valido, controlla!");
        // chiudi la scheda chrome se il link non è valido
        driver.quit();
        }


    }
    public StringBuilder leggiFile(){

        /*String sitoFilePath = System.getProperty("user.dir") + File.separator+"src" + File.separator + "test" + File.separator + "resources" + File.separator + "sito.txt";*/

        String sitoFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\sito.txt";
        System.out.println(sitoFilePath);
        File sitoFile = new File(sitoFilePath);
        StringBuilder dato = new StringBuilder();

        System.out.println("Sono dentro LeggiFile");

        try(Scanner scan = new Scanner(sitoFile)){
            while(scan.hasNext()){
                dato.append(scan.nextLine());
            }
            return dato;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Non è stato trovato il file indicato!!");
        }

    }

    @When("I click on {} button")
    public void removeCookies(String textButton) {
        homePage.clickOnButton(textButton);
        homePage.takeScreenshoot();

    }

    @Then("Hide all ads")
    public void hideAllAds() {

    }


    @And("I remove ads and take screenshot")
    public void removeAds() {
        if (!homePage.getLogoBanner().isDisplayed()) {
            homePage.takeScreenshoot();
            homePage.getButtonCloseAds().click();
        } else {
            System.out.println("No ADS were found!");
        }
    }

    @Then("I research for \"{}\"")
    public void researchNew(String context) {
        homePage.getCercaButton().click();
        // Non serve cliccarci evvidentemente
        // homePage.getInputField().click();
        homePage.getInputField().sendKeys(context);
        homePage.getInputField().sendKeys(Keys.ENTER);
        // Non serve più siccome uso ENTER sul FORM
        //homePage.getSubmitResearcButton().click();

    }

    @And("I scroll to bottom")
    public void scrollToBottom() {
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
    public void quitPage() {
        driver.quit();
    }
}
