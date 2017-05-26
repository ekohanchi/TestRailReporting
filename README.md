# testrail-integration
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
testrail.username={YOUR_USERNAME_FOR_TESTRAIL}
testrail.password={YOUR_PASSWORD_FOR_TESTRAIL}
```

Create an environment variable called `TESTRAIL_INT_HOME` and point it to where the conf directory is located

```
$ vim ~/.bash_profile
export TESTRAIL_INT_HOME={PATH_TO_CONF_DIRECTORY}
$ . ~/.bash_profile
```

## Startup
To start up the application:
```
mvn spring-boot:run
```

## Access
To access the application, on your browser navigate to: http://localhost:8080

