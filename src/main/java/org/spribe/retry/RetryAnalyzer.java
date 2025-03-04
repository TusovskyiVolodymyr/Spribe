package org.spribe.retry;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Log4j2
public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY_COUNT = Integer.parseInt(System.getProperty("retriesCount", "3"));

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (shouldRetry()) {
            if (count < MAX_RETRY_COUNT) {
                count++;
                log.info("Retrying test {}, retry number {}", iTestResult.getMethod().getQualifiedName(), count);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean shouldRetry() {
        return System.getProperty("CI") != null;
    }
}
