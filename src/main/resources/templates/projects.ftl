<!DOCTYPE HTML>
<html lang="en" style="overflow-y: scroll;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

        <title>Home - TestRail Project Summary</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/"><img src="img/testrailNcenterfield.png" width="200" height="30" class="d-inline-block align-top" alt="">TestRail Project Summary</a>
            <div class="collapse navbar-collapse" id="navbarNav">
            <!--
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="runsclosure">Close Test Runs</a>
                    </li>
                </ul>
             -->
            </div>
        </nav>

        <div class="container my-5">
            <div class="container my-5">
                <div class="container my-5">
                    <div class="row mb-3">
                        <p class="lead text-secondary">Last Updated on: ${todaysDate!"NULL"}</p>
                    </div>
                    <div class="row">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr class="table-primary">
                                    <th scope="col">ID</th>
                                    <th scope="col">Project</th>
                                    <th scope="col">Total Test Cases</th>
                                    <th scope="col">Total Automated</th>
                                    <th scope="col">Not Automatable</th>
                                    <th scope="col">% Automated</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#list projects as project>
                                    <tr>
                                        <td scope="row"><b>${project.projectId}</b></td>
                                        <td>${project.projectName!"NULL"}</td>
                                        <td>${project.totalTcs!"NULL"}</td>
                                        <td>${project.totalAutoTcs!"NULL"}</td>
                                        <td>${project.notAutomatableTcs!"NULL"}</td>
                                        <td>${project.autoPercentage!"NULL"}</td>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.2.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
