package listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/extent.html");
            spark.config().setReportName("Sorting Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}