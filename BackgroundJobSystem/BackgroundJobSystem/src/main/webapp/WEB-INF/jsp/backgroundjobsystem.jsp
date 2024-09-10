<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Background Job System</title>
</head>
<body>
	<h1>Add Job</h1>
	<form method="POST" action="/addjob">
		<label for="jobName">Job Name</label>
		<input type="text" value="jobName"/>
		<label for="maxAttempts">Max Attempts</label>
		<input type="text" value="maxAttempts"/>
	</form>

	<h1>Run Job</h1>


	<h1>History</h1>
	<form method="POST" action="/enqueue">
		<table title="History">
			<tr>
				<th>Job</th>
			</tr>
			<tr>
				<th>Start Time</th>
			</tr>
			<tr>
				<th>End Time</th>
			</tr>
			<tr>
				<th>Successful?</th>
			</tr>
			<core:forEach var="attempt" items="${history}">
				<tr>
					<td>${attempt.jobId}</td>
				</tr>
				<tr>
					<td>${attempt.startTime}</td>
				</tr>
				<tr>
					<td>${attempt.endTime}</td>
				</tr>
				<tr>
					<td>${attempt.successful}</td>
				</tr>
			</core:forEach>
		</table>
	</form>
</body>
</html>