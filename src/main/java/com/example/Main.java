package com.example;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, TesseractException {
        WebDriverManager.chromedriver().setup();
        WebDriver WebDriver = new ChromeDriver();
        try{
        WebDriver.manage().window().maximize();
        WebDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebDriver.get("https://www.irctc.co.in/nget/train-search");
        WebDriver.findElement(By.xpath("/html/body/app-root/app-home/div[1]/app-header/div[1]/div[2]")).click();
        WebDriver.findElement(By.xpath("//*[@id=\"slide-menu\"]/p-sidebar/div/nav/div/label/button")).click();
        WebElement imageElement = WebDriver.findElement(By.className("captcha-img"));
        File src = imageElement.getScreenshotAs(OutputType.FILE);
        String path = "C:\\Formation Selenium\\demomaven\\src\\main\\resources\\image\\imagecaptcha.png";
        FileHandler.copy(src, new File(path));
        Thread.sleep(2000);
        ITesseract image = new Tesseract();
        String str = image.doOCR(new File(path));
        System.out.println("Hello world!");
        System.out.println("Le contenu d'image est : "+str);
        WebDriver.findElement(By.id("captcha")).sendKeys(str);
        }catch(IOException a)
        {
            System.out.println(a.getMessage());
        }
        
    }
}