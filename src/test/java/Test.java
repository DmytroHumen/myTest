import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private final WebDriver driver = new ChromeDriver();

    @org.junit.Test
    public void isBookContains() {

        Book expectedBook = null;
        Book actualBook = null;

        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver\\chromedriver.exe");

        driver.manage().window().maximize();

        driver.get("https://www.amazon.de/-/en/Kathy-Sierra/dp/3960092067/ref=sr_1_15?dib=eyJ2IjoiMSJ9.ImfedxT-qUd-vVYm075VXx2v6gQefHUcSOUMwV6GibiRZfBG3n2KITBFitic6X1KmmvoVxZXOrm7RbvO4TVGANz5xAxcaFB4IKdYnwc0y09z-I9DRySy3Oblnb1xVOzJ_L3-yOPopdpI4BOz_QMNqYJ9fAGpyNY0lFklX5DMDsTWfLZVsc4x5qFHZwmD6sn2AYRCqu4FlhXcPob6NfY0hg2CucbIDpOTJsTYe1qd1Ig.jf6XMIiJ91A9Npg4pFzGGcZ3bftSYtPw3CpAPaNP-n8&dib_tag=se&keywords=Java&qid=1714745786&s=books&sr=1-15");

        WebElement clickAccept = driver.findElement(By.xpath("//span[contains(text(), \"Accept\")]/../input"));
        clickAccept.click();

        String bookName = driver.findElement(By.id("productTitle")).getText();
        String author = driver.findElement(By.id("bylineInfo")).getText();
        String price = driver.findElement(By.xpath("//span[contains(text(), \"49\")]")).getText();
        boolean isBestseller = driver.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();

        expectedBook = new Book(bookName, author, price, isBestseller);
        expectedBook.setTextBook(String.valueOf(driver.findElement(By.xpath("//div[@data-a-expander-name=\"book_description_expander\"]//p"))));

        driver.get("https://www.amazon.com.ua/");

        WebElement filterBooks = driver.findElement(By.linkText("Books"));
        filterBooks.click();

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java");
        searchBox.submit();

        List<Book> books = new ArrayList<>();

        List<WebElement> results = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));
        for (WebElement result : results) {
            bookName = result.findElement(By.className("a-size-medium")).getText();
            author = result.findElement(By.className("a-size-base")).getText();
            price = result.findElement(By.className("a-price")).getText();
            isBestseller = !result.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();

            Book book = new Book(bookName, author, price, isBestseller);
            books.add(book);
        }

        for (Book book1 : books) {
            if (book1.getBookName().contains("Java von Kopf bis Fuß: Eine abwechslungsreiche Entdeckungsreise durch die objektorientierte Programmierung")) {
                WebElement clickLink = driver.findElement(By.xpath("//*[contains(text(), \"Java von Kopf bis Fuß: Eine abwechslungsreiche Entdeckungsreise durch die objektorientierte Programmierung\")]"));
                clickLink.click();
                actualBook = book1;
                actualBook.setTextBook(String.valueOf(driver.findElement(By.xpath("//div[@data-a-expander-name=\"book_description_expander\"]//p"))));
            }
        }
        System.out.println(expectedBook.equals(actualBook));
        Assert.assertEquals(expectedBook, actualBook);
        Assert.assertTrue("Book not found", books.contains(expectedBook));
    }
    @After
    public void afterTest() {
       driver.quit();
    }
}



