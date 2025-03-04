package player;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.apache.logging.log4j.ThreadContext;
import org.spribe.config.YamlConfig;
import org.spribe.logging.RestAssuredLogger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public abstract class BaseTest {


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        String env = System.getProperty("TEST_ENV", "qa");
        YamlConfig.loadConfig(env);

        RestAssured.baseURI = YamlConfig.getString("restassured.baseUri");

        String allureLogin = YamlConfig.getString("enable_allure");
        String enable_logging = YamlConfig.getString("enable_logging");

        if (allureLogin.equals("true")) {
            RestAssured.filters(new AllureRestAssured());
        }
        if (enable_logging.equals("true")) {
            RestAssured.filters(new RestAssuredLogger());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        ThreadContext.put("threadId", Thread.currentThread().getName());
        ThreadContext.put("testName", method.getName());
    }
}
