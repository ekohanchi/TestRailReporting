<!DOCTYPE HTML>
<html lang="en" style="overflow-y: scroll;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

        <title>Run Type Report - TestRail Project Summary</title>
        
	            <!--Load the AJAX API-->
	    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	
	      // Load the Visualization API and the corechart package.
	      google.charts.load('current', {'packages':['corechart']});
	
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.charts.setOnLoadCallback(drawChart);
	
	      // Callback that creates and populates a data table,
	      // instantiates the pie chart, passes in the data and
	      // draws it.
	      function drawChart() {
	
	    	// Setting variable values
	    	var runName = "${name!"NULL"}";
	    	
	        // Create the data table.
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'Type');
	        data.addColumn('number', 'Count');
	        data.addRows([
	          ['Acceptance', ${acceptance!"NULL"}],
	          ['Accessibility', ${accessibility!"NULL"}],
	          ['Automated', ${automated!"NULL"}],
	          ['Compatibility', ${compatibility!"NULL"}],
	          ['Destructive', ${destructive!"NULL"}],
	          ['Functional', ${functional!"NULL"}],
	          ['Other', ${other!"NULL"}],
	          ['Performance', ${performance!"NULL"}],
	          ['Regression', ${regression!"NULL"}],
	          ['Security', ${security!"NULL"}],
	          ['Smoke & Sanity', ${smoke_sanity!"NULL"}],
	          ['Usability', ${usability!"NULL"}],
	          ['Not Automatable', ${not_automatable!"NULL"}]
	        ]);
	        
	
	        // Set chart options
	        var options = {'title': runName,
			        		'is3D':true,
			        		'width':400,
	                       'height':300};
	
	        // Instantiate and draw our chart, passing in some options.
	        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
	      }
	    </script>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/"><img src="img/testrail.png" width="75" height="30" class="d-inline-block align-top" alt=""></a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="runsclosure">Close Test Runs</a>
                    </li>
                </ul>
            </div>
        </nav>
        
        <p class="small">
        <b>Test RunId:</b> ${testRunId!"NULL"}
        </p>
        <!--Div that will hold the pie chart-->
        <div id="chart_div"></div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>
