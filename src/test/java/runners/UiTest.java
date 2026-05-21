package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.DataProvider;
import common.framework.TestListener;

@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {
                "ui.stepdefinitions",
                "ui.hooks"
        },
        plugin = {
                "pretty",
                "html:target/ui-cucumber-report.html"
        }
)
@Listeners(TestListener.class)
public class UiTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
