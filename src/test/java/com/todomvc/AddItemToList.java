package com.todomvc;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class AppTest {

  @Test
  public void shouldAnswerWithTrue() {
    WebDriver wd = new ChromeDriver();
    wd.get("https://todomvc.com/examples/angularjs/#/");
    wd.quit();
    assertTrue(true);
  }
}
