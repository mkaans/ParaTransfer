package org.example;

import com.thoughtworks.gauge.Step;
import driver.Driver; // Driver sınıfını içe aktarıyoruz.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class metodlar {
    private WebDriver driver;
    private Map<String, String> kaydedilenDegerler = new HashMap<>(); // Değerleri saklamak için Map

    // Constructor (Driver sınıfındaki webDriver'ı kullanır)
    public metodlar() {
        this.driver = Driver.webDriver; // Driver sınıfından webDriver'ı alıyoruz.

    }

    @Step("<elementTipi> tipindeki <element> elementinin değeri kaydedilen şu değere eşit mi <kayitAdi>")
    public void verifyElementValuesSaved(String elementTipi, String element, String kayitAdi) {
        By locator = getLocator(elementTipi, element);

        // Elementi bul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // Elementin değerini al
        String actualValue = webElement.getText();
        if (actualValue.isEmpty()) {
            actualValue = webElement.getAttribute("value"); // Eğer getText boşsa, input alanları için "value" alınır
        }

        // Kaydedilen değeri getir
        String beklenenDeğer = getirKaydedilenDeger(kayitAdi);

        // Değeri karşılaştır
        if (actualValue.equals(beklenenDeğer)) {
            System.out.println("Doğrulama başarılı: Elementin değeri '" + actualValue + "' beklenen değerle eşleşiyor.");
        } else {
            throw new AssertionError("Doğrulama başarısız: Elementin değeri '" + actualValue + "', ancak beklenen değer '" + beklenenDeğer + "' oldu.");
        }
    }
    @Step("<elementTipi> tipindeki <element> elementinin değerini al ve <degerAdi> adıyla kaydet")
    public void kaydetElementDegeri(String elementTipi, String element, String degerAdi) {
        By locator = getLocator(elementTipi, element);

        // FluentWait kullanarak elementi bekle
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        String elementDegeri = webElement.getText(); // Elementin text değerini al
        if (elementDegeri.isEmpty()) {
            elementDegeri = webElement.getAttribute("value"); // Eğer text boşsa, input gibi alanlardan değer al
        }

        kaydedilenDegerler.put(degerAdi, elementDegeri); // Değeri sakla
        System.out.println(degerAdi + " kaydedildi: " + elementDegeri);
    }

    @Step("<degerAdi> adıyla kaydedilen değeri getir")
    public String getirKaydedilenDeger(String degerAdi) {
        if (!kaydedilenDegerler.containsKey(degerAdi)) {
            throw new AssertionError("Kaydedilen değer bulunamadı: " + degerAdi);
        }
        return kaydedilenDegerler.get(degerAdi);
    }

    @Step("<elementTipi> tipindeki <element> elementinin görünürlüğünü kontrol et")
    public void checkElementVisibility(String elementTipi, String element) {
        By locator = getLocator(elementTipi, element);

        // Wait for the element to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        if (webElement.isDisplayed()) {
            System.out.println("Element görünür durumda."+element);
        } else {
            throw new AssertionError("Element görünür değil: " + element);
        }
    }

    @Step("<elementTipi> tipindeki <element> elementinin tıklanabilirliğini kontrol et ve tıkla")
    public void checkElementClickabilityAndClick(String elementTipi, String element) {
        By locator = getLocator(elementTipi, element);

        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));

        if (webElement.isDisplayed() && webElement.isEnabled()) {
            webElement.click();
            System.out.println("Element tıklanabilir ve başarıyla tıklandı."+element);
        } else {
            throw new AssertionError("Element tıklanabilir değil: " + element);
        }
    }

    @Step("<elementTipi> tipindeki <element> elementinin tıklanabilirliğini kontrol et")
    public void checkElementClickability(String elementTipi, String element) {
        By locator = getLocator(elementTipi, element);

        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));

        if (webElement.isDisplayed() && webElement.isEnabled()) {
            System.out.println("Element tıklanabilir durumda."+element);
        } else {
            throw new AssertionError("Element tıklanabilir değil: " + element);
        }
    }

    @Step("<elementTipi> tipindeki <element> elementine tıkla")
    public void clickElement(String elementTipi, String element) {
        By locator = getLocator(elementTipi, element);

        // Elementi bul ve tıkla
        WebElement webElement = driver.findElement(locator);
        if (webElement.isDisplayed() && webElement.isEnabled()) {
            webElement.click();
            System.out.println("Element başarıyla tıklandı."+element);
        } else {
            throw new AssertionError("Element tıklanabilir değil: " + element);
        }
    }

    @Step("<elementTipi> tipindeki <element> text alanına <text> değerini yaz")
    public void clearAndSendText(String elementTipi, String element, String text) {
        By locator = getLocator(elementTipi, element);

        // Elementi bul ve text alanını temizle
        WebElement webElement = driver.findElement(locator);
        if (webElement.isDisplayed() && webElement.isEnabled()) {
            webElement.clear(); // Alanı temizliyoruz
            webElement.sendKeys(text); // Yeni değeri yazıyoruz
            System.out.println("Text alanına '" + text + "' değeri yazıldı.");
        } else {
            throw new AssertionError("Text alanına erişilemiyor: " + element);
        }
    }
    @Step("<seconds> saniye bekle")
    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // Saniyeyi milisaniyeye çevirerek bekleme yap
            System.out.println(seconds + " saniye bekleniyor...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Thread’in kesilme durumunu koru
            System.out.println("Bekleme sırasında bir hata oluştu: " + e.getMessage());
        }
    }
    @Step("<expectedUrl> URL'sinin doğru olduğunu kontrol et")
    public void verifyUrl(String expectedUrl) {
        // Mevcut sayfanın URL'sini al
        String currentUrl = driver.getCurrentUrl();

        // URL'yi doğrula
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Doğrulama başarılı: Mevcut URL '" + currentUrl + "' beklenen URL ile eşleşiyor.");
        } else {
            throw new AssertionError("URL doğrulama başarısız: Beklenen URL '" + expectedUrl + "', ancak mevcut URL '" + currentUrl + "' oldu.");
        }
    }

    @Step("<elementTipi> tipindeki <element> elementinin değeri şuna eşit mi <beklenenDeğer>")
    public void verifyElementValue(String elementTipi, String element, String beklenenDeğer) {
        By locator = getLocator(elementTipi, element);

        // Elementi bul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // Elementin değerini al
        String actualValue = webElement.getText();

        // Değeri karşılaştır
        if (actualValue.equals(beklenenDeğer)) {
            System.out.println("Doğrulama başarılı: Elementin değeri '" + actualValue + "' beklenen değerle eşleşiyor.");
        } else {
            throw new AssertionError("Doğrulama başarısız: Elementin değeri '" + actualValue + "', ancak beklenen değer '" + beklenenDeğer + "' oldu.");
        }
    }


    // Tek bir metod ile locator'ı belirleme
    private By getLocator(String elementTipi, String element) {
        switch (elementTipi.toLowerCase()) {
            case "id":
                return By.id(element);
            case "name":
                return By.name(element);
            case "xpath":
                return By.xpath(element);
            case "css":
                return By.cssSelector(element);
            case "class":
                return By.className(element);
            case "tag":
                return By.tagName(element);
            default:
                throw new IllegalArgumentException("Desteklenmeyen element tipi: " + elementTipi);
        }
    }
}
