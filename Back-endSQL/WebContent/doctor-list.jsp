<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/registration-lists_style.css" /></style>
	<title>Doctor Registration</title>
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
	
	<div class="main-page">
		<div class="row">
		<div class="container">
			<a href="<%=request.getContextPath()%>/listDoctor" class="refresh-list"><button type = "button" class = "refreshList-button">Show Doctor List</button></a>
			<br>
			<div style="overflow-x:auto;">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th></th>
						<th>Doctor ID</th>
						<th>Name</th>
						<th>SSN</th>
						<th>Address</th>
						<th>Email</th>
						<th>Community ID</th>
						<th>Hospital ID</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="doctor" items="${listDoctor}">

						<tr>
							<td><a href="approveDoctor?ssn=<c:out value='${doctor.ssn}' />">Accept</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="deleteDoctor?ssn=<c:out value='${doctor.ssn}' />" onClick="return confirm('Are you sure you want to Deny this doctor's registration?');" >Deny</a></td>
							<td><c:out value="${doctor.doctorid}" /></td>
							<td><c:out value="${doctor.name}" /></td>
							<td><c:out value="${doctor.ssn}" /></td>
							<td><c:out value="${doctor.address}" /></td>
							<td><c:out value="${doctor.email}" /></td>
							<td><c:out value="${doctor.communityid}" /></td>
							<td><c:out value="${doctor.hospitalid}" /></td>
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
			</div>
		</div>
	</div>
	</div>
</body>
</html>