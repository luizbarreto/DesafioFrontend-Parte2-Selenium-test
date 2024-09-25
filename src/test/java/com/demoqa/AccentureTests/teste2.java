package com.demoqa.AccentureTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class teste2 {
    public static void main(String[] args) {
        // Defina o caminho correto do ChromeDriver (caminho onde você extraiu o ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        // Inicialize o WebDriver (Chrome)
        WebDriver driver = new ChromeDriver();

        try {
            //Acessar o site
            driver.get("https://demoqa.com/");

            //Escolher a opção Alerts, Frame & Windows
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Alerts, Frame & Windows']"))).click();

            //Clicar em Browser Windows
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Browser Windows']"))).click();

            //Clicar no botão New Window
            wait.until(ExpectedConditions.elementToBeClickable(By.id("windowButton"))).click();

            //Checar que nova janela foi aberta
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            //Pegar as handles das janelas
            List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

            //Mudar o foco para a nova janela aberta
            driver.switchTo().window(windowHandles.get(1));

            //Validar a mensagem “This is a sample page”
            String bodyText = driver.findElement(By.tagName("body")).getText();
            if (bodyText.contains("This is a sample page")) {
                System.out.println("Mensagem encontrada: 'This is a sample page'.");
            } else {
                System.out.println("Mensagem não encontrada.");
            }

            //Fechar a nova janela
            driver.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Fechar navegador
            driver.quit();
        }
    }
}
