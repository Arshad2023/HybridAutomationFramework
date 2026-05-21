# HybridAutomationFramework

This framework is created for UI and API automation testing.

UI tests are written for SauceDemo application and API tests are written for JSONPlaceholder APIs.

## Tools Used

- Java
- Maven
- Selenium WebDriver
- Cucumber
- TestNG
- RestAssured
- ExtentReports
- Log4j2
- Apache POI

## Basic Flow

The framework is divided into UI, API and common packages.

UI package contains page classes, step definitions, hooks and reusable action methods.

API package contains API services, step definitions, hooks, request builder, response builder and validation utility.

Common package contains config reader, report manager, retry analyzer and listener classes.

Feature files are kept separately for UI and API scenarios.

Test data is maintained separately from test logic.

## UI Test Cases

SauceDemo URL:

```text
https://www.saucedemo.com/
```

UI scenarios covered:

- TC-01: Successful login and inventory landing
- TC-02: Locked-out user validation
- TC-03: Product sorting by price low to high
- TC-04: Cart add, remove and count validation
- TC-05: Complete checkout with item total validation

## API Test Cases

API Base URL:

```text
https://jsonplaceholder.typicode.com
```

API scenarios are created for:

- Posts
- Comments
- Albums
- Photos
- Users

## Important Framework Features

Page Object Model is used for UI automation.

RestAssured is used for API automation.

Cucumber feature files are used to write readable test scenarios.

TestNG is used to run the Cucumber tests.

Excel file is used for UI test data.

JSON file is used for API test data.

ExtentReports are used for HTML reporting.

Log4j2 is used for execution logs.

Screenshots are captured during UI execution.

## Parallel Execution

Parallel execution is added using TestNG and Cucumber DataProvider.

`ThreadLocal<WebDriver>` is used for UI tests so each parallel scenario gets a separate browser instance.

`ThreadLocal<Response>` is used for API tests so API responses do not get mixed during parallel execution.

`ThreadLocal<ExtentTest>` is used so Extent report logs are maintained separately for each running scenario.

## Retry Analyzer

Retry analyzer is added using TestNG `IRetryAnalyzer`.

If any scenario fails, the framework retries it based on the retry count configured in `RetryAnalyzer`.

Retry details are also written in the log file.

## Test Listener

A TestNG listener is added to capture execution events.

It logs suite start, suite finish, scenario start, pass, fail, skip and execution time.

## Config

Config file:

```text
src/test/resources/config.properties
```

It contains values like browser, UI base URL, API base URL and headless mode.

## How To Run

Run all tests:

```bash
mvn test
```

Run only UI tests:

```bash
mvn "-Dtest=runners.UiTest" test
```

Run only API tests:

```bash
mvn "-Dtest=runners.ApiTest" test
```

Run by tag:

```bash
mvn test "-Dcucumber.filter.tags=@api"
```

## Reports, Logs and Screenshots

Extent report:

```text
reports/
```

Cucumber report:

```text
target/
```

Screenshots:

```text
reports/screenshots/
```

Logs:

```text
logs/automation.log
```

## Test Data

UI test data:

```text
src/test/resources/testdata/TestData.xlsx
```

API test data:

```text
src/test/resources/testdata/apiTestData.json
```

## Author

Arshad Rangrez
