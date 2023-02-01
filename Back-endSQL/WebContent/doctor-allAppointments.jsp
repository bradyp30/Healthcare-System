<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><jsp:directive.include file="css-files/global_style.css" /></style>
<style><jsp:directive.include file="css-files/patientAppointments_style.css" /></style>
<title>My Appointments</title>
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
	                		<a href = "">Appointments</a>
	                		<div class  = "dropdownContent doctorDropdown">
	                			<a href="doctor-appointments.jsp" class = "dropOption">Schedule Appointment</a>
	                			<a href="doctor-allAppointments.jsp">My Appointments</a>
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
	                		<a href = "">Register</a>
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

    
    <div class="row">
    	
    	<!-- Collapsing Sections to display upcoming and past appointments -->
    	
    	<!-- Upcoming appointments (appointments that are today or later -->
    	<button type = "button" class = "upcomingAppts">Upcoming Appointments</button>
    	<div class = "upcomingApptsDiv">
    		<!-- for each appointment where the day has not passed -->
    		<c:forEach var="appointment" items="${uniquedates}">
    		
    				<button type = "button" class = "collapse-appts apptDateDisplay"><c:out value="${appointment}" /></button>
	    			<div id = "call" class = "apptDates myApppointmentsContainer timeWrapping">
			    		
			    		<c:forEach var="times" items="${listBookedVisits}">
			    		<c:if test="${(appointment).equals(times.date)}">
				   			<div class = "apptOption">
								<h2 class = "apptTimeUpcoming"><c:out value="${times.time}" /></h2>
								<h2 class = "cancelApptTime"><a href="Appointmentvisit?id=<c:out value='${times.id}' />&name=<c:out value='${times.ssn}' />" onclick = "return confirm('Start Appointment?')">Start Appointment</a></h2>
							</div>
						</c:if>
			
						</c:forEach>
						
		    		</div>
    			
    			
    		</c:forEach>
    	</div>
    	
		
	</div>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		var collapse = document.getElementsByClassName("collapse-appts");
		var i;
		
	
		
		for (i = 0; i < collapse.length; i++) {
			collapse[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var apptDates = this.nextElementSibling;
				
				
				if (apptDates.style.display === "flex") {
					apptDates.style.display = "none";
					

				} else {
					apptDates.style.display = "flex";
					
				}
			});
		}
		
		
	</script>
</body>
</html>












