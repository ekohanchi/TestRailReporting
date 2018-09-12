<!DOCTYPE HTML>
<html lang="en" style="overflow-y: scroll;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

        <title>Home - TestRail Project Summary</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <!-- <a class="navbar-brand" href="/"><img src="img/testrail.png" width="200" height="30" class="d-inline-block align-top" alt="">TestRail Project Summary</a> -->
            <a class="navbar-brand" href="/"><img src="${pageLogo}" width="200" height="30" class="d-inline-block align-top" alt=""></a>
            <div class="collapse navbar-collapse" id="navbarNav">
            
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="runsclosure">Close Test Runs</a>
                    </li>
                </ul>
             
            </div>
        </nav>
        <h5>TestRail Project Summary</h5>
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


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>
