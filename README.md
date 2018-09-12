# TestRail-Reporting
This is a spring boot application, that makes a call to the testrail api and retrievs a list of all projects and within a table displays the total count of test cases, total count of automated test cases, and percentage of automated test cases. In order for the application to start up correctly it must first be configured.

## Configure

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
```

Create an environment variable called `TESTRAIL_REPORT_HOME` and point it to where the conf directory is located

```
$ vim ~/.bash_profile
export TESTRAIL_REPORT_HOME={PATH_TO_CONF_DIRECTORY}
$ . ~/.bash_profile
```

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

