import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.time.Duration;

public class TestSelenium {
/*    private static WebDriver driver =new ChromeDriver();

    public static WebElement clickButton(String xpath){
        WebElement button = driver.findElement(By.xpath(xpath));
        button.click();
    }*/







    public static void main(String[] args) throws Exception {



//        da cercare chromeoptions -> dovete fare in modo che il test parta a schermo intero
//
//        qualsiasi elemento e o  azione che dovete fare vi serve il webdriver
//        cercate webdriver -> come istanziare il webdriver e passare le chromeoptions
//
//
//        per interagire con gli elementi dovete usare WebElement.. cercate questo e esplorate anche i suggerimenti di intellij. affidatevi alla doc di selenium disponibile online per tutti i metodi necessari
//

    // Dichiarazione del driver (motere di ricerca) da utilizzare, in questo caso chrome.

        WebDriver driver = new ChromeDriver();

    // Indirizzazione del motere di ricerca verso un URL
        driver.get("https://www.lastampa.it/");

    // FullScreen
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();

    // Elemento per la ricezione del titolo della pagina
        driver.getTitle();

    /*
        Attesa sicura del caricamento della pagina da parte del driver, utile per pagine
        che appena entri, dopo un secondo ti mostra una finestralla con: cookies, abbonamento o anche pubblicità.
        Aspettando dici: ok caricati comletamente, cosi posso operare su tutto.
    */
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    // Ricerca dell'elemento 'BUTTON' per accettare la richiesta cookies.
        WebElement acceptBtn = driver.findElement(By.xpath("//button[text() = 'Accetta']"));


        // Click sul 'BUTTON' per accettare e chiudere la scheda.
        acceptBtn.click();

        // Prova Screenshot solo se pubblicità
   /*     try {
         WebElement bannerPubblicitario = driver.findElement(By.xpath("//div[@id='layer']"));
            if(bannerPubblicitario.isDisplayed()){
                File scrFile = bannerPubblicitario.getScreenshotAs(OutputType.FILE);
                FileHandler.copy(scrFile, new File("C:\\Users\\Gabriel Bejan\\Desktop\\Esercizi\\Task_1\\screenshoot\\screnshoot.png"));
            }
        } catch (RuntimeException e) {
            *//*throw new RuntimeException(e);*//*
        }finally {
            System.out.println("Ok");
        }*/
        WebElement pagina = driver.findElement(By.tagName("html"));
        File scrFile = pagina.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(scrFile, new File("C:\\Users\\Gabriel Bejan\\Desktop\\Esercizi\\Task_1\\screenshoot\\screnshoot.png"));


                // Ricerca elemento 'CERCA' Dentro la pagina per poter poi inserire Testo.
        System.out.println("DEBUG: Ricerca bottone cerca");
        WebElement cercaButton = driver.findElement(By.xpath("(//button/span[text() = 'Cerca']/ancestor::button)[2]"));

        System.out.println("Premi ricerca");
        cercaButton.click();

        System.out.println("Ricerca Campo Input");
        WebElement testoRicerca = driver.findElement(By.xpath("//form[1]//div[1]//input[@type='search']"));

        //testoRicerca.click();

        System.out.println("Inserimento in testo 'Input' ");
        testoRicerca.sendKeys("milano");

        System.out.println("Invio ricerca");
        WebElement invioButton = driver.findElement(By.xpath("//form[@method='get']//button[@type='submit']"));
        invioButton.click();


        System.out.println("Scrollare");
        WebElement footer = driver.findElement(By.tagName("footer"));

        System.out.println(footer);

        new Actions(driver)
                .scrollToElement(footer)
                .perform();

        driver.quit();
    }
}





