<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/doctor-registration.css" /></style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Viewing <%=(String)(request.getAttribute("testname")) %></title>
<script type="text/javascript">
window.onload = function() { 
 
<% if(request.getAttribute("list") != null) { %>
var chart = new CanvasJS.Chart("chartContainer", {
	backgroundColor: null,
	
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text:"<%=(String)(request.getAttribute("testname")) %>", 
	},
	axisX: {
		interval:1,
        intervalType: "day"
		
	},
	axisY: {
		title: "<%=(String)(request.getAttribute("unit")) %>",
		
	},
	data: [{
		color: "#e69500",
		type: "<%=(String)(request.getAttribute("chart_type")) %>", //change type to bar, line, area, pie, etc
		xValueType: "dateTime",
		dataPoints: <%out.print(request.getAttribute("list"));%>
	}]
});
chart.render();
<% } %> 
 
}
</script>
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
    <div class = "main-body">
		<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	</div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>        