<!DOCTYPE HTML>
<html lang="en" style="overflow-y: scroll;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

        <title>Close Test Runs - TestRail Project Summary</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/"><img src="img/testrail.png" width="75" height="30" class="d-inline-block align-top" alt="">TestRail Project Summary</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="runsclosure">Close Test Runs</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container my-5">
            <div class="container my-5">
                <div class="container my-5">
                    <div class="container">
                        <div class="row">
                            <h6>WARNING: This will close all existing test runs for a given project id and archive its tests and results.</h6>
                            <h6>Closing a test run cannot be undone.</h6>
                        </div>
                        <div class="row my-5">
                            <div class="container">
                                <form class="form-inline" name="runsclosure" action="closeRuns" method="post">
                                    <div class="form-group">
                                        <label>Project ID: </label>
                                        <input class="form-control mx-3" type="text" placeholder="12345" name="project_id">
                                    </div>
                                    <button type="submit" class="btn btn-primary mx-3">Submit</button>
                                </form>
                            </div>
                        </div>
                        <hr class="my-4 my-5">
                        <div class="row">
                            <div class="col-3">Project ID Specified:</div>
                            <div class="col">${projectId!"NULL"}</div>
                        </div>
                        <div class="row my-3">
                            <div class="col-3">Project Name:</div>
                            <div class="col">${projectName!"NULL"}</div>
                        </div>
                        <div class="row my-3">
                            <div class="col-3">Message:</div>
                            <div class="col">${message!"NULL"}</div>
                        </div>
                        <div class="row my-3">
                            <div class="col-3">Open Test Runs Count Before:</div>
                            <div class="col">${openTestRunsBefore!"NULL"}</div>
                        </div>
                        <div class="row my-3">
                            <div class="col-3">Open Test Runs Count After:</div>
                            <div class="col">${openTestRunsAfter!"NULL"}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.2.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
