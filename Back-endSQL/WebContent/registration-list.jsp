<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/doctor-registration.css" /></style>
	<title>Doctor Registration</title>
</head>
<body>
	<header>
        <nav class="navbar">
            <div id = "navbar-text">
                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
            </div>

            <ul class="navbar-nav">
                <li><a href="patient-registration.jsp">Patient Registration</a></li>
                <li><a href="doctor-registration.jsp">Doctor Registration</a></li>
                <!-- <li><a href="">Community Member Registration</a></li> -->
                <li><a href="Hospital-Owner-Registration.jsp">Hospital Owner Registration</a></li>
                <li id = "nav-login"><a href="login-form.jsp">Login</a></li>
                <!-- <li id = "nav-register"><a href="patient-registration.jsp">Register</a></li> -->
            </ul>
        </nav>
    </header>


	<div class="row">
		<div class="container">
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ssn</th>
						<th>name</th>
						<th>address</th>
						<th>birthdate</th>
						<th>email</th>
						<th>familyid</th>
						<th>prevfamilyid</th>
						<th>communityid</th>
						<th>doctorid</th>

					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="Patient" items="${listPatient}">
						<tr>
							<td><a href="edit?id=<c:out value='${Patient.patientSSN}' />" style = "text-decoration: underline">Approve</a>&nbsp;&nbsp;&nbsp;&nbsp; 
                                <a href="delete?id=<c:out value='${Patient.patientSSN}' />" style = "text-decoration: underline" onclick = "return confirm('Are you sure you want to deny this patient's registration form?')">Deny</a></td>
							<td><c:out value="${Patient.patientSSN}" /></td>
							<td><c:out value="${Patient.patientName}" /></td>
							<td><c:out value="${Patient.patientAddress}" /></td>
							<td><c:out value="${Patient.patientBirthday}" /></td>
							<td><c:out value="${Patient.patientEmail}" /></td>
							<td><c:out value="${Patient.patientFamilyID}" /></td>
							<td><c:out value="${Patient.patientPrevFamilyID}" /></td>
							<td><c:out value="${Patient.patientCommID}" /></td>
							<td><c:out value="${Patient.patientDocID}" /></td>
							<!-- Password if needed -->
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
		</div>
	</div>
	
	
	
	
	<div class="row">
		<div class="container">
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>doctorid</th>
						<th>name</th>
						<th>address</th>
						<th>communityid</th>
						<th>hospitalid</th>

					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="Patient" items="${listPatient}">

						<tr>
							<td><c:out value="${Doctor.doctorid}" /></td>
							<td><c:out value="${Doctor.name}" /></td>
							<td><c:out value="${Doctor.address}" /></td>
							<td><c:out value="${Doctor.communityid}" /></td>
							<td><c:out value="${Doctor.hospitalid}" /></td>
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
