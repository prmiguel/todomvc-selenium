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
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddItemToList {

  @Test
  public void shouldAddItemToEmptyList() {
    String task = "Task 1";
    WebDriver wd = new ChromeDriver();
    wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get("https://todomvc.com/examples/angular/dist/browser/#/all");
    wd.findElement(By.cssSelector("input.new-todo")).sendKeys(task, Keys.ENTER);
    Wait<WebDriver> wait = new WebDriverWait(wd, Duration.ofSeconds(5).getSeconds());
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".main .todo-list label")));
    assertEquals(1, wd.findElements(By.cssSelector(".main .todo-list label")).size());
    assertEquals(task, wd.findElements(By.cssSelector(".main .todo-list label")).get(0).getText());
    wd.quit();
  }

  @Test
  public void shouldAddItemToListWithExistingItems() {
    String[] existingTasks = { "Task 1", "Task 2", "Task 3" };
    String newTask = "Task 4";
    WebDriver wd = new ChromeDriver();
    wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get("https://todomvc.com/examples/angular/dist/browser/#/all");
    Arrays.asList(existingTasks).stream()
        .forEach(task -> wd.findElement(By.cssSelector("input.new-todo")).sendKeys(task, Keys.ENTER));
    wd.findElement(By.cssSelector("input.new-todo")).sendKeys(newTask, Keys.ENTER);
    assertEquals(4, wd.findElements(By.cssSelector(".main .todo-list label")).size());
    assertTrue(wd.findElements(By.cssSelector(".main .todo-list label")).stream()
        .anyMatch(we -> we.getText().equals(newTask)));
    wd.quit();
  }
}
