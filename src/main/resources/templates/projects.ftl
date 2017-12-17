<!DOCTYPE HTML>
<html>
<head>
<title>Project Summary</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div class="container container-navbar">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Test Cases & Project Summary</a>
        </div>
    </div>
    <div class="container api-container">

        <!-- Main content. -->
        <div class="table-content">
            <!-- Tables. -->
	    <div class="curdate">Last Updated on: ${todaysDate!"NULL"}</div>
            <div class="envs">Test Cases Per Project List</div>
            <table class="striped">
                <thead>
                    <tr>
                        <th>Project ID</th>
                        <th>Project</th>
                        <th>Total Test Cases</th>
                        <th>Total Automated Test Cases</th>
                        <th>Not Automatable Test Cases</th>
                        <th>Percentage Automated Test Cases </th>
                    </tr>
                </thead>
                <tbody>
                <!-- 
                	<tr>
                		<td>1</td>
                		<td>Sample project</td>
                		<td>100</td>
                		<td>50</td>
                	</tr>
                	-->
                
                    <#list projects as project>
                    <tr>
                        <td><b>${project.projectId}</b></td>
                        <td class="vertext">${project.projectName!"NULL"}</td>
                        <td class="vertext">${project.totalTcs!"NULL"}</td>
                        <td class="vertext">${project.totalAutoTcs!"NULL"}</td>
                        <td class="vertext">${project.notAutomatableTcs!"NULL"}</td>
                        <td class="vertext">${project.autoPercentage!"NULL"}</td>
                    </tr>
                    </#list>
                    
                </tbody>
            </table>
        </div>
        <!-- /Main content. -->
    </div>
</body>
</html>
