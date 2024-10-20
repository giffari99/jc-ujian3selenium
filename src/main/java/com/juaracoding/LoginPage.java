package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver","C:\\my tools\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.printf("Open Browser");
        driver.get("https://www.saucedemo.com/");


        Utils.delay(2);
        loginInvalidUser(driver, "standard_user","secret_saucee");
        Utils.delay(2);


        //  Positive Login
        loginValidUser(driver, "standard_user", "secret_sauce");
        String txtProducts = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        System.out.println(txtProducts);

        // assert Login
        if (txtProducts.contains("Products")) {
            System.out.println("Login berhasil. Halaman Products ditemukan.");
        } else {
            System.out.println("Login gagal. Halaman Products tidak ditemukan.");
        }


        //quit driver
        Utils.delay(2);
        driver.quit();
        System.out.println("Quit Browser");
    }


    public static void loginInvalidUser(WebDriver driver, String username, String password) {

        // --------------------  invalid login 1 hanya memasukan username
        driver.findElement(By.id("user-name")).sendKeys(username);
        Utils.delay(1);
        driver.findElement(By.id("login-button")).click();
        Utils.delay(1);
        driver.findElement(By.id("user-name")).sendKeys(Keys.CONTROL + "A");
        Utils.delay(1);
        driver.findElement(By.id("user-name")).sendKeys(Keys.DELETE);
        Utils.delay(1);

        // --------------------  invalid login 2 hanya memasukan password
        driver.findElement(By.id("password")).sendKeys("password");
        Utils.delay(1);
        driver.findElement(By.id("login-button")).click();
        Utils.delay(1);
        driver.findElement(By.id("password")).sendKeys(Keys.CONTROL + "A");
        Utils.delay(1);
        driver.findElement(By.id("password")).sendKeys(Keys.DELETE);
        Utils.delay(1);

        // --------------------  invalid login 3 username benar dan password salah
        driver.findElement(By.id("user-name")).sendKeys(username);
        Utils.delay(1);
        driver.findElement(By.id("password")).sendKeys(password);
        Utils.delay(1);
        driver.findElement(By.id("login-button")).click();
        Utils.delay(1);
        driver.findElement(By.id("user-name")).sendKeys(Keys.CONTROL + "A");
        Utils.delay(1);
        driver.findElement(By.id("user-name")).sendKeys(Keys.DELETE);
        Utils.delay(1);
        driver.findElement(By.id("password")).sendKeys(Keys.CONTROL + "A");
        Utils.delay(1);
        driver.findElement(By.id("password")).sendKeys(Keys.DELETE);
        Utils.delay(1);


    }
    public static void loginValidUser(WebDriver driver, String username1, String password1) {
        // -----------------------  positive login
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username1);
        Utils.delay(1);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password1);
        Utils.delay(1);
        driver.findElement(By.id("login-button")).click();

        // -------------------  klick add 1 product to cart
        Utils.delay(2);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Utils.delay(2);
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        Utils.delay(2);
        driver.navigate().back();


}
}
