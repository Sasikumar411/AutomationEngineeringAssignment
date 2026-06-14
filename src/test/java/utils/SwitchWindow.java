package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwitchWindow {

    public static void switchToNewWindow(WebDriver driver) {

        try {

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> d.getWindowHandles().size() > 1);

            List<String> windows =
                    new ArrayList<>(driver.getWindowHandles());

            driver.switchTo().window(windows.get(1));

        } catch (Exception e) {

            System.out.println("Product opened in the same window.");
        }
    }
}