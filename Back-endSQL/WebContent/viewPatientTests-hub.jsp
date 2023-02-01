<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/patientViewTestHub_style.css" /></style>
	<title>View Entries</title>
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
	                			<a href="<%=request.getContextPath()%>/listOpenAppointments" class = "dropOption">Schedule Appointment</a>
	                			<a href="<%=request.getContextPath()%>/listBookedAppointments">My Appointments</a>
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
    
	<form method="post" action="patientGraphChoice">
	<!--
		<div id = "addTestData"> 
			<a href="patient-testdata.jsp"><button type = "button" class = "buttons">Add Vitals</button></a>
		</div>
	-->
	<div class = "main-body">
		
		<div class = "row">
			<div class = "testChoice" id = "bloodPressure">

				
				<div><button type = "submit" name = "testName" value = "Blood Pressure">Blood Pressure</button></div>

			</div>
			<div class = "testChoice" id = "pulse">

				
			
				<div><button type = "submit" name = "testName" value = "Pulse">Pulse</button></div>

			</div>
		</div>
		<div class = "row">
			<div class = "testChoice" id = "temperature">

				
				<div><button type = "submit" name = "testName" value = "Temperature">Temperature</button></div>

			</div>
			<div class = "testChoice" id = "spo2Choice">

				<div><button type = "submit" name = "testName" value = "Oxygen">SpO2</button></div>
			</div>
		</div>
		<div class = "row">
			<div class = "testChoice" id = "glucose">
				
				<div><button type = "submit" name = "testName" value = "Glucose">Glucose</button></div>
			</div>
			<div class = "testChoice" id = "weight">
				

				<div><button type = "submit" name = "testName" value = "Weight">Weight</button></div>

			</div>
		</div>
	</div>
	</form>
</body>
</html>