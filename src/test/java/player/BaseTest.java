import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.spribe.logging.RestAssuredLogger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

@Configuration
@ComponentScan(value = "org.spribe")
@ContextConfiguration(classes = BaseTest.class)
@DirtiesContext()
@Log4j2
public abstract class BaseTest extends AbstractTestNGSpringContextTests {


    @BeforeSuite
    public void beforeSuite() {
        RestAssured.baseURI = "http://3.68.165.45";
        RestAssured.filters(
                new AllureRestAssured(), new RestAssuredLogger());
    }

    @BeforeMethod()
    public void beforeMethod(Method method) {
        ThreadContext.put("threadId", Thread.currentThread().getName());
        ThreadContext.put("testName", method.getName());
    }
}
