<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><jsp:directive.include file="css-files/global_style.css" /></style>
<style><jsp:directive.include file="css-files/patientTests_style.css" /></style>
<title>Enter Patient Test Data</title>
</head>
<body>
<header>
        <nav class="navbar">
            <div id = "navbar-text">
                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
            </div>

            <ul class="navbar-nav">
            	<c:if test="${user == 'patient'}">
	                <li><a href="viewPatientTests-hub.jsp">View Tests</a></li>
	                <li><a href="<%=request.getContextPath()%>/listTest">Enter Vitals</a></li>
	                <li id = "patientDrop">
	                	<div class = "dropdown">
	                		<a>Appointments</a>
	                		<div class  = "dropdownContent patientDropdown">
	                			<a href="patient-makeAppointment.jsp" class = "dropOption">Schedule Appointment</a>
	                			<a href="patient-allAppointments.jsp">My Appointments</a>
	                		</div>
	                	</div>
	                </li>
                </c:if>
                <c:if test="${user != null}">
                	<li id = "nav-login"><a href="login-form.jsp">Logout</a></li>
				</c:if>
				<c:if test="${user == null}">
                	<li id = "nav-login"><a href="login-form.jsp">Login</a></li>
				</c:if>
                
                <li id = "nav-register">
                
                	<div class = "dropdown">
                		<a>Register</a>
                		<div class  = "dropdownContent">
                			<a href="patient-registration.jsp" class = "dropOption">Patient</a>
                			<a href="doctor-registration.jsp">Doctor</a>
                			<a href="community-owner.jsp">Community Member</a>
                			<a href="Hospital-Owner-Registration.jsp">Hospital Owner</a>
                		</div>
                	</div>

                </li>
            </ul>
        </nav>
    </header>
    
    <!-- Patient values will include Blood Pressure (mmHg) - X, Pulse (bpm) - X, SpO2 (%) - X, Temperature (F) - X, Weight (lbs)
    			Glucose (mg/dL) -->
    
    <div class="main-page">
    <h1 id = "currentDate">Today's Date: <%= (LocalDate.now()).toString() %></h1>
    	<form method="post" action="insertData">
    	
		<div class="row">
		<div class="container">
			
			<table class = "patientTestTable">
				<thead>
						<tr>
							<th><input type='text' name='ssnTest' id='ssnTest' class = "patientTestID" min="0" hidden = "true"/></th><!-- SSN Identifier -->
							<th>Test Name</th> <!-- Test Name in database -->
							<th>Enter Data</th>	<!-- User data value -->
							<th></th> <!-- Automatically grab date -->
						</tr>
				</thead>
				<tbody>
					
						<tr>
							<td></td> <!-- Patient social security # -->
							<td class = "testLabelValues">Blood Pressure</td> <!-- Should this be test.bloodPressure -->
							<td><input type='number' name='bloodPressureDataUpper' id='bloodPressureDataUpper' class = "patientTestData bloodPressData" min="0" max = "300" required/> / 
								<input type='number' name='bloodPressureDataLower' id='bloodPressureDataLower' class = "patientTestData bloodPressData" min="0" max = "300" required/> mmHg</td>
							<td></td>
						</tr>
						
						<tr>
							<td></td> <!-- Patient social security # -->
							<td class = "testLabelValues">Pulse</td> <!-- Should this be test.pulse -->
							<td><input type='number' name='pulseData' id='pulseData' class = "patientTestData" min="0" max = "500" required/> bpm</td>
							<td></td>
						</tr>
						
						<tr>
							<td></td> <!-- Patient social security # -->
							<td class = "testLabelValues">Temperature</td> <!-- Should this be test.temperature -->
							<td><input type='number' name='temperatureData' id='temperatureData' class = "patientTestData" min="50" max = "120" step = "0.1" required/> &#176;F</td>
							<td></td>
						</tr>
						
						<tr>
							<td></td> <!-- Patient social security # -->
							<td class = "testLabelValues">SpO2</td> <!-- Should this be test.spo2 -->
							<td><input type='number' name='spo2Data' id='spo2Data' class = "patientTestData" min="0" max = "100" required/> %</td>
							<td></td>
						</tr>
						
						<tr>
							<td></td> <!-- Patient social security # -->
							<td class = "testLabelValues">Glucose</td> <!-- Should this be test.glucose -->
							<td><input type='number' name='glucoseData' id='glucoseData' class = "patientTestData" min="0" max = "3000" required/> mg/dL</td>
							<td></td>
						</tr>
						
						<tr>
							<td></td> <!-- Patient social security # -->
							<td class = "testLabelValues">Weight</td> <!-- Should this be test.weight -->
							<td><input type='number' name='weightData' id='weightData' class = "patientTestData" min="0" max = "1500" step = "0.1" required/> lbs</td>
							<td></td>
						</tr>
					
				</tbody>
			</table>
		</div>
		</div>
		<div class = "login-buttons-container">
			<button type = "submit" formmethod = "POST" id = "patientTestData"  class = "buttons">Submit Test</button>
			<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
		</div>
	</form>
	</div>
</body>
</html>