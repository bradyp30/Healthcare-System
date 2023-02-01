<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style><jsp:directive.include file="css-files/global_style.css" /></style>
<style><jsp:directive.include file="css-files/doctor-visithistory.css" /></style>

<title>Create New Patient Test</title>

</head>
<body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
var i = 0;
$(function() {
	
	$.get('getusermedication', function(jsonresponse) {
		
	$.each(jsonresponse, function(index, item) {
		var $d = $("<div />", {
	        id: "d" + i,
	        class: 'container'
		});
		var $f =$("<input />", {
			type: "text",
			id: "f" + i,
			name: "m",
			value: jsonresponse[index].prescription
		});
		
		i = i+1;
		$d.append($f);
		$d.append('<button type="submit" value="remove" class = "remove">Remove</button>');
		//$d.append('<input type="button" value="remove" class = "remove"/>');
		$('#medtext').append($d);
	});	
		
	});
	$.get('getuserlab', function(jsonresponse) {
		
		$.each(jsonresponse, function(index, item) {
			var $d = $("<div />", {
		        id: "d" + i,
		        class: 'container'
			});
			var $f =$("<input />", {
				type: "text",
				id: "f" + i,
				name: "m",
				value: jsonresponse[index].lab
			});
			
			i = i+1;
			$d.append($f);
			$d.append('<button type="submit" value="remove" class = "remove">Remove</button>');
			//$d.append('<input type="button" value="remove" class = "remove"/>');
			$('#labtext').append($d);
		});	
			
		});
	

	$("#Addmed").on("click", function() {
		var $d = $("<div />", {
	        id: "d" + i,
	        class: 'container'
		});
		var $f =$("<input />", {
			type: "text",
			id: "f" + i,
			name: "m",
			placeholder: "Enter new medication"
		});
		
		i = i+1;
		$d.append($f);
		$d.append('<button type="submit" value="remove" class = "remove">Remove</button>');
		//$d.append('<input type="button" value="remove" class = "remove"/>');
		$('#medtext').append($d);
		
	});
	$('body').on('click','.remove',function() {
	    $(this).parent('div.container').remove()

	  });
	$("#Addlab").on("click", function() {
		var $d = $("<div />", {
	        id: "d" + i,
	        class: 'container'
		});
		var $f =$("<input />", {
			type: "text",
			id: "f" + i,
			name: "l",
			placeholder: "Enter new lab test"
		});
		
		i = i+1;
		
		$d.append($f);
		$d.append('<button type="submit" value="remove" class = "remove">Remove</button>');
		//$d.append('<input type="button" value="remove" class = "remove"/>');
		$('#labtext').append($d);
		
	});
	
});
	

</script>
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
    	<div id = "visit-details" style="overflow-x:auto;">
        <form method="post" id="test" action="testing">
    	<table>
    		<h1 style="margin-bottom: 0; margin-left: 5px"><u>Visit Details</u></h1>
			<tr>
				<td class = "left">Patient Symptoms</td>
				<td class = "middle"></td>
				<td class = "right window-style">Medications <button type = "button" id = "Addmed" class = "buttons">Add</button></td>
			</tr>
			<tr>
				<td class = "left">
					<textarea name='symptoms' id='symptoms' placeholder = "Enter symptoms..." rows="6" cols="30" required></textarea>
				</td>
				<td class = "middle"></td>
				<td id = "medtext" class = "right"></td>
			</tr>
			<tr class = "table-empty">
			</tr>
			<!--  Start of row -->
			<tr>
				<td class = "left">Visit Summary</td>
				<td class = "middle"></td>
				<td class = "right window-style">Lab Tests <button type = "button" id = "Addlab" class = "buttons">Add</button></td>
			</tr>
			<tr>
				<td class = "left">
					<textarea name='summary' id='summary' placeholder = "Enter summary..." rows="6" cols="50" required></textarea>
				</td>
				<td class = "middle"></td>
				<td id = "labtext" class = "right"></td>
			</tr>
			<tr>
				<td class = "left">
				<div class = "login-buttons-container">
					<button type = "submit" formmethod = "POST" id = "createTestSubmit"  class = "buttons">Submit</button>
					<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
				</div>
				</td>
				<td></td>
			</tr>
		
		</table>
		
    
	    
		</form>
		</div>
		<div id = "list-visit-summaries" style="overflow-y:auto;">
			<div id = "appt-refresh">
				<h1 class = "underlined-headings">Appointment History</h1>
				<a href="<%=request.getContextPath()%>/listDoneAppointments" class="refresh-list"><button type = "button" class = "refreshList-button">Refresh</button></a>
			</div>
			<c:forEach var="appointment" items="${listDoneVisits}">
				<div class = "apptDetailsList">
				<h2><c:out value="${appointment.date}" /></h2>
	
				<h3>Symptoms</h3>
				<p><c:out value="${appointment.symptoms}" /></p>
				
				<h3>Visit Summary</h3>
				<p><c:out value="${appointment.summary}" /></p>
				</div>
			</c:forEach>
		</div>
    </div>
</body>
</html>