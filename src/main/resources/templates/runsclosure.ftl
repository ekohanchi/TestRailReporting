<!DOCTYPE HTML>
<html lang="en" style="overflow-y: scroll;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- <link rel="stylesheet" href="static/css/bootstrap2.min.css"> -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

        <title>Close Test Runs - TestRail Project Summary</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/"><img src="img/testrail.png" width="75" height="30" class="d-inline-block align-top" alt=""></a>
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
        <h5>Close Test Runs</h5>

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
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>
