package com.todomvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClearCompletedTodos {

    @Test
    public void shouldBeAbleToClearCompletedTodos() {
        String[] existingTasks = { "Task 1", "Task 2" };
        WebDriver wd = new ChromeDriver();
        wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        Arrays.asList(existingTasks).stream()
                .forEach(task -> wd.findElement(By.cssSelector("input.new-todo")).sendKeys(task, Keys.ENTER));
        wd.findElement(By.xpath("//*[@class='view' and contains(.,'Task 2')]//input[@type='checkbox']")).click();
        wd.findElement(By.cssSelector(".clear-completed")).click();
        assertEquals(1, wd.findElements(By.cssSelector(".main .todo-list label")).size());
        assertTrue(wd.findElements(By.cssSelector(".main .todo-list label")).stream()
                .anyMatch(we -> !we.getText().equals("Task 2")));
        wd.quit();
    }

    @Test
    public void shouldNotBeAbleToClearCompletedTodosIfNoneAreComplete() {
        String[] existingTasks = { "Task 1", "Task 2" };
        WebDriver wd = new ChromeDriver();
        wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        Arrays.asList(existingTasks).stream()
                .forEach(task -> wd.findElement(By.cssSelector("input.new-todo")).sendKeys(task, Keys.ENTER));
        assertEquals(2, wd.findElements(By.cssSelector(".main .todo-list label")).size());
        wd.quit();
    }
}
