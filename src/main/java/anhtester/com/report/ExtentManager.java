package anhtester.com.report;

import anhtester.com.helpers.Props;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(Props.getValue("extentReportPath"));
        reporter.config().setReportName(Props.getValue("reportName"));
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", Props.getValue("reportName"));
        extentReports.setSystemInfo("Author", Props.getValue("author"));
        return extentReports;
    }
}
