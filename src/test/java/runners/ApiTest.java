package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.DataProvider;
import common.framework.TestListener;

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {
                "api.stepdefinitions",
                "api.hooks"
        },
        plugin = {
                "pretty",
                "html:target/api-cucumber-report.html"
        }
)
@Listeners(TestListener.class)
public class ApiTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
