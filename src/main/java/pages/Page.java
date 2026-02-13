package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class Page implements PageInterface{
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;

        // Istruzione necessaria per far funzionare i @FindBy. Siccome sono elementi di PageFactory
        // ha bisogno di essere inizializzata prima di essere usata
        PageFactory.initElements(driver, this);

    }

    public void openPage(String url){
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }
}
