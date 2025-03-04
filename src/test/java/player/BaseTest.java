package player;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.spribe.config.YamlConfig;
import org.spribe.logging.RestAssuredLogger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

@Log4j2
public abstract class BaseTest {


    @BeforeSuite
    public void beforeSuite() {
        String env = System.getProperty("TEST_ENV", "qa");
        YamlConfig.loadConfig(env);

        RestAssured.baseURI = YamlConfig.getString("restassured.baseUri");

        String doLogin = YamlConfig.getString("enable_allure");

        RestAssured.filters(
                new AllureRestAssured(), new RestAssuredLogger());
    }

    @BeforeMethod()
    public void beforeMethod(Method method) {
        ThreadContext.put("threadId", Thread.currentThread().getName());
        ThreadContext.put("testName", method.getName());
    }
}
