package com.todomvc;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AddItemToList {

  @Test
  public void shouldAddItemToEmptyList() {
    String task = "Task 1";
    WebDriver wd = new ChromeDriver();
    wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get("https://todomvc.com/examples/angularjs/#/");
    wd.findElement(By.cssSelector("input.new-todo")).sendKeys(task, Keys.ENTER);
    Wait<WebDriver> wait = new WebDriverWait(wd, Duration.ofSeconds(5).getSeconds());
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.main .todo-list label")));
    assertEquals(1, wd.findElements(By.cssSelector("section.main .todo-list label")).size());
    assertEquals(task, wd.findElements(By.cssSelector("section.main .todo-list label")).get(0).getText());
    wd.quit();
  }
}

