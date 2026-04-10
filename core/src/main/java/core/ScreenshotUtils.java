package core;

import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtils {

    public static void captureScreenshot(WebDriver driver, String testName) {

        try {

            if (driver == null) return;

            String fileName = testName + "_" + System.currentTimeMillis() + ".png";

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + fileName;

            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(path));

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}