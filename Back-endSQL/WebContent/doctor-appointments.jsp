<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style><jsp:directive.include file="css-files/global_style.css" /></style>
<style><jsp:directive.include file="css-files/patient-registration_style.css" /></style>

<title>Doctor Appointment Page</title>
</head>
<body>
	<header>
	        <nav class="navbar">
	            <div id = "navbar-text">
	                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
	            </div>
	            <ul class="navbar-nav">
		            <c:if test="${user == 'doctor'}">
		            	<li><a href="<%=request.getContextPath()%>/listAbnormalData">My Patients</a></li>
		            	<li id = "doctorDrop">
	                	<div class = "dropdown">
	                		<a>Appointments</a>
	                		<div class  = "dropdownContent doctorDropdown">
	                			<a href="doctor-appointments.jsp" class = "dropOption">Create New Appointment</a>
	                			<a href="<%=request.getContextPath()%>/listDoctorAppointments">My Appointments</a>
	                		</div>
	                	</div>
	               		</li>
		            </c:if>
	                <c:if test="${user == 'doctor'}">
	                	<li id = "nav-login"><a href="login-form.jsp">Logout</a></li>
					</c:if>
					<c:if test="${user != 'doctor'}">
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
    
    <div class = "main-body">
        <form method="post" action="insertAppointment">
    	<table>
    		<h1 style="margin-bottom: 0; margin-left: 5px"><u>Doctor Appointment Page</u></h1>
			<tr>
				<td>Date</td>
				<td>Time</td>
			</tr>
			<tr>
				<td>
					<input type='date' name='apptDate' id='apptDate' class = "createApptForm" placeholder="YYYY-MM-DD" pattern="\d{4}-?\d{1-2}-?\d{1-2}" 
							min="<%= (LocalDate.now()).toString() %>"required/>
				</td>
				<td>
					<input type='time' name='apptTime' id='apptTime' class = "createApptForm" placeholder="HH:MM:SS" pattern="\d{1-2}:?\d{1-2}:?\d{2}" 
							min = "08:00:00" max = "17:00:00" required/>
				</td>
			</tr>
			
		</table>
    
	    <div class = "login-buttons-container">
			<button type = "submit" formmethod = "POST" id = "createTestSubmit"  class = "buttons">Create Appointment</button>
			<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
		</div>
	    
		</form>
    </div>

</body>
</html>