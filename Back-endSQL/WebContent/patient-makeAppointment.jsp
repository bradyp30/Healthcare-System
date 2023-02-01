<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><jsp:directive.include file="css-files/global_style.css" /></style>
<style><jsp:directive.include file="css-files/patientAppointments_style.css" /></style>

<title>Available Appointments</title>
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
    
    <div class="row">
		<div class="container">
			<br>

			<form method="post" action="listOpenAppointments">
				<input type = "date" name = "searchDate" id = "searchApptDate" min="<%= (LocalDate.now()).toString() %>" required>
				
				<button type="submit" id="searchPatientAppt"  class ="buttons">Search</button>
			</form>
			
			<div style="overflow-x:auto;">
			
			<% 	if (request.getParameter("searchDate") != null) {
					LocalDate searchedDate = LocalDate.parse(request.getParameter("searchDate"));
					int day = searchedDate.getDayOfMonth();
					String month = searchedDate.getMonth().toString();
					String strMonth = month.substring(0, 1) + month.substring(1).toLowerCase();
					
					int year = searchedDate.getYear();
				
					String strDate = " " + strMonth + " " + day + ", " + year;
			%>		
				<h1>Appointments Available on <%= strDate %>:</h1>
			<%	} %>		
			
				<div class = "apppointmentContainer">
				
					<c:forEach var="appointment" items="${listOpenVisits}">
						
						<a href="bookAppointment?id=<c:out value='${appointment.id}'/>" onclick="return alert('Your appointment has been booked')">
						<div class = "apptOption">
							<h1 id = "apptTimeAvail"><c:out value="${appointment.time}" /></h1>
						</div>
						</a>

							
					</c:forEach>
				</div>
						

			</div>
			
		</div>
	</div>
</body>
</html>