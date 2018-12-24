# TestRail-Reporting
This is a spring boot application, that makes a call to the testrail api and retrievs a list of all projects and within a table displays the total count of test cases, total count of automated test cases, and percentage of automated test cases. In order for the application to start up correctly it must first be configured.

## Configure - Application

```bash
# create a directory called conf
$ mkdir conf
$ cd conf
# within the directory create a file called application.properties
$ vim application.properties
```

Within the file the following content should be added:

```
testrail.baseurl={YOUR_URL_DOMAIN_FOR_TESTRAIL}
testrail.username={YOUR_USERNAME_FOR_TESTRAIL}
testrail.password={YOUR_PASSWORD_FOR_TESTRAIL}
page.logopath={PATH_TO_IMAGE_IN src.main.resources.static.img - i.e. img/images.png}

# Values for custom test case statuses
testrail.status.customstatus1={"Custom Status 1"}
testrail.status.customstatus2={"Custom Status 2"}
testrail.status.customstatus3={"Custom Status 3"}
testrail.status.customstatus4={"Custom Status 4"}
testrail.status.customstatus5={"Custom Status 5"}
testrail.status.customstatus6={"Custom Status 6"}
testrail.status.customstatus7={"Custom Status 7"}

```

Create an environment variable called `TESTRAIL_REPORT_HOME` and point it to where the conf directory is located

```
$ vim ~/.bash_profile
export TESTRAIL_REPORT_HOME={PATH_TO_CONF_DIRECTORY}
$ . ~/.bash_profile
```

## Configure - TestRail
In order for this application to work optimally, the following configuration changes need to be made to your TestRail instance:

* within Administration > Customizations 
* add a new field called "Automation Status"
* the type for this new field should be a Dropdown
* the options for this field should be:

```
1, Not Automated
2, Automated
3, Not Automatable
```

* default value should be set to: "Not Automated"

## Startup
To start up the application:
```
mvn spring-boot:run
```
## Build as war file
To build the application as a war file:
```
mvn clean package
```

## Access
To access the application, on your browser navigate to: http://localhost:8080

## Endpoint Usage
**Broad Status Report:** Provides graphical status of test runs for given testRunIds, i.e. pass/fail/untested. testRunIds are required. This method can be used for either a single or multiple test runs across different projects.

* **URL:**
```
/runallstatusreport?testRunIds={testRunId1,testRunId2,testRunId3}
```

* **Method:**
`GET`

**Status Report:** Provides graphical status of test run for a given testRunId, i.e. pass/fail/untested. testRunId is required.

* **URL:**
```
/runstatusreport?testRunId={testRunId}
```

* **Method:**
`GET`

**Type Report:** Provides graphical view of the different types of test cases for a given testRunId, i.e. manual/automated.


* **URL:**
```
/runtypereport?testRunId={testRunId}
```

* **Method:**
`GET`

## Screenshots
View of the home page when the application is first started up:
![Home Page](screenshots/MainPage.png?raw=true "Home Page")
View of the 'Close Test Runs' page which can be used to close all test runs within a given project id:
![Close All Test Runs](screenshots/CloseTestRuns.png?raw=true "Close All Test Runs")
A call to the 'Broad Status Report' endpoint which can be used to get the status of test runs across different projects
![Broad Status Report](screenshots/RunAllStatusReport.png?raw=true "Broad Status Report")




