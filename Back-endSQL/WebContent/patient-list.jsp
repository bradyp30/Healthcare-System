<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- adding changes to gitlab -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/registration-lists_style.css" /></style>
	<title>Patient Registration List Overview</title>
</head>
<body>
	<header>
        <nav class="navbar">
            <div id = "navbar-text">
                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
            </div>

            <ul class="navbar-nav">
                <c:if test="${user != null}">
                	<li id = "nav-login"><a href="login-form.jsp">Logout</a></li>
				</c:if>
				<c:if test="${user == null}">
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
		<div class="container">
			<a href="<%=request.getContextPath()%>/listPatient" class="refresh-list"><button type = "button" class = "refreshList-button">Show Patient List</button></a>
			<br>
			<div style="overflow-x:auto;">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th></th>
						<th>SSN</th>
						<th>Name</th>
						<th>Address</th>
						<th>Birth Date</th>
						<th>E-mail</th>
						<th>Family ID</th>
						<th>Previous Family ID</th>
						<th>Community ID</th>
						<th>Doctor ID</th>
						<!-- Password (if needed) -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="patient" items="${listPatient}">
						<tr>
							<td><a href="approvePatient?ssn=<c:out value='${patient.ssn}' />" style = "text-decoration: underline">Approve</a>&nbsp;&nbsp;&nbsp;&nbsp; 
                                <a href="deletePatient?ssn=<c:out value='${patient.ssn}' />" style = "text-decoration: underline">Deny</a></td>
							<td><c:out value="${patient.ssn}" /></td>
							<td><c:out value="${patient.name}" /></td>
							<td><c:out value="${patient.address}" /></td>
							<td><c:out value="${patient.birthdate}" /></td>
							<td><c:out value="${patient.email}" /></td>
							<td><c:out value="${patient.familyid}" /></td>
							<td><c:out value="${patient.prevfamilyid}" /></td>
							<td><c:out value="${patient.communityid}" /></td>
							<td><c:out value="${patient.doctorid}" /></td>
							<!-- Password if needed -->
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
			</div>
		</div>
	</div>
	
	
</body>
</html>
