<!DOCTYPE HTML>
<html>
<head>
<title>Runs Closure</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <h2>Close Test Runs</h2>
    <form name="runsclosure" action="closeRuns" method="post">
    	WARNING: This will close all existing test runs for a given project id and archive its tests & results.<br>
    	<b>Closing a test run cannot be undone.</b><br>
        Project ID: <input size="5" maxlength="5" type="text" name="project_id">
        <br> <input type="submit">
    </form>
    <br> Project ID Specified: ${projectId!"NULL"}
    <br> Project Name: ${projectName!"NULL"}
    <br> Message: ${message!"NULL"}
    <br> Open Test Runs Count Before: ${openTestRunsBefore!"NULL"}
    <br> Open Test Runs Count After: ${openTestRunsAfter!"NULL"}
</body>
</html>