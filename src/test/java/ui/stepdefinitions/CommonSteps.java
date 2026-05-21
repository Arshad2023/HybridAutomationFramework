package ui.stepdefinitions;

import io.cucumber.java.en.Given;
import ui.Base.BaseTest;
import ui.pages.LoginPage;
import ui.utils.TestLogger;

public class CommonSteps extends BaseTest {

    @Given("User launches the application for test case {string}")
    public void user_launches_the_application(String testCaseID) {
        TestLogger.logInfo("Navigating to login page");
        if (data == null) {
            throw new RuntimeException("No data found in Excel for TestCaseID: " + testCaseID);
        }
    }
    @Given("User enters login credentials and submits")
    public void user_Enter_Creds(){
        LoginPage loginPage = new LoginPage(BaseTest.getDriver());
        loginPage.login(data.get("Email"), data.get("Password"));
    }
}
