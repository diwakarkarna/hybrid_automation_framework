package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sample extends BaseClass{
    @Test
    public void loginTest(){
        //WebDriver driver = getDriver();
        driver.get("https://admin-demo.nopcommerce.com/login");
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://admin-demo.nopcommerce.com/admin/");

    }
}
