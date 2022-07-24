package secondTask;

import org.testng.*;

import javax.net.ssl.SSLEngineResult;
import java.io.ObjectInputFilter;

public class TestListener implements ITestListener {
    private int m_count = 0;
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("testStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("on success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("on failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("on skipped");
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("on finish");
    }
}
