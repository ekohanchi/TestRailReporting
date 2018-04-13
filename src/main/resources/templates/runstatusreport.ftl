<!DOCTYPE HTML>
<html lang="en" style="overflow-y: scroll;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script defer src="js/fontawesome-all.js"></script>

        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

        <title>Run Status Report - TestRail Project Summary</title>
        
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
	    	/* var isCompleted = "${is_completed?string('TRUE','FALSE')!"NULL"}"; */
	    	var passedCount = ${passed_count!"NULL"};
	    	var blockedCount = ${blocked_count!"NULL"};
	    	var untestedCount = ${untested_count!"NULL"};
	    	var retestCount = ${retest_count!"NULL"};
	    	var failedCount = ${failed_count!"NULL"};
	    	var customStatus1 = ${custom_status1!"NULL"};
	    	var customStatus2 = ${custom_status2!"NULL"};
	    	var customStatus3 = ${custom_status3!"NULL"};
	    	var customStatus4 = ${custom_status4!"NULL"};
	    	var customStatus5 = ${custom_status5!"NULL"};
	    	var customStatus6 = ${custom_status6!"NULL"};
	    	var customStatus7 = ${custom_status7!"NULL"};
	    	
	        // Create the data table.
	        var data = new google.visualization.DataTable();
	        data.addColumn('string', 'Status');
	        data.addColumn('number', 'Count');
	        data.addRows([
	          ['Passed', passedCount],
	          ['Blocked', blockedCount],
	          ['Untested', untestedCount],
	          ['Retest', retestCount],
	          ['Failed', failedCount],
	          ['Custom Status 1', customStatus1],
	          ['Custom Status 2', customStatus2],
	          ['Custom Status 3', customStatus3],
	          ['Failed Existing', customStatus4],
	          ['Custom Status 5', customStatus5],
	          ['Custom Status 6', customStatus6],
	          ['Custom Status 7', customStatus7]
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
            <a class="navbar-brand" href="/"><img src="img/testrail.png" width="75" height="30" class="d-inline-block align-top" alt="">TestRail Project Summary</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="runsclosure">Close Test Runs</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="runstatusreport">Run Status Report</a>
                    </li>
                </ul>
            </div>
        </nav>
        
        <p class="small">
        <b>Test Completion Status:</b> ${is_completed?string('TRUE','FALSE')!"NULL"}<br>
        <b>Test RunId:</b> ${testRunId!"NULL"}
        </p>
        <!--Div that will hold the pie chart-->
        <div id="chart_div"></div>

        <script src="js/jquery-3.2.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
