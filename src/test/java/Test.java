import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Test {

    private WebDriver driver = new ChromeDriver();

    @org.junit.Test
    public void isBookContains() {

        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver\\chromedriver.exe");

        driver.manage().window().maximize();

        driver.get("https://www.amazon.com.ua/");

        WebElement filterBooks = driver.findElement(By.linkText("Books"));
        filterBooks.click();

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java");
        searchBox.submit();

        List<Books> books = new ArrayList<>();

        List<WebElement> results = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));
        for (WebElement result : results) {
            String bookName = result.findElement(By.cssSelector("h2")).getText();
            String author = result.findElement(By.cssSelector(".a-row.a-size-base.a-color-secondary span.a-size-base")).getText();
            String price = result.findElement(By.cssSelector(".a-price .a-offscreen")).getText();
            boolean isBestseller = !result.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();

            Books book = new Books(bookName, author, price, isBestseller);
            books.add(book);
        }

        boolean containsHeadFirstJava = false;
        for (Books books1 : books) {
            if (books1.getBookName().contains("Head First Java")) {
                containsHeadFirstJava = true;
            }
        }

        Assert.assertTrue(containsHeadFirstJava);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}




