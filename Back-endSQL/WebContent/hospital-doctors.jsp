<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/registration-lists_style.css" /></style>
	<title>Doctors in my Hospital</title>
</head>
<body>
	<header>
        <nav class="navbar">
            <div id = "navbar-text">
                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
            </div>

            <ul class="navbar-nav">
               
                <c:if test="${user == 'hospital'}">
               		<li><a href="<%=request.getContextPath()%>/listpatienthospital">Hospital Patients</a></li>
               		<li><a href="<%=request.getContextPath()%>/listdoctorhospital">Hospital Doctors</a></li>
                	<li id = "nav-login"><a href="login-form.jsp">Logout</a></li>
				</c:if>
				<c:if test="${user != 'hospital'}">
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
			
			<br>
			<div style="overflow-x:auto;">
			<table class="commPatient-table">
				<thead>
					<tr>
						<th class = "list table-approval"></th>
						<th class = "list table-ssn">SSN</th>
						<th class = "list table-name">Name</th>
						<th class = "list table-date">Birth Date</th>
						<th class = "list table-address">Address</th>
						<th class = "list table-email">E-mail</th>
						<th class = "list table-viewID">Doctor ID</th>
						<th class = "list table-viewID">Hospital ID</th>
						<th class = "list table-viewID">Community ID</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="doctor" items="${listhospitaldoctor}">
						<tr>
							<td class = "list table-approval">
								<c:if test="${doctor.accepted == 0}">
									<a href="approveDoctor?ssn=<c:out value='${doctor.ssn}' />">Approve</a>&nbsp;&nbsp;&nbsp;&nbsp; 
                               	 	<a href="deleteDoctor?ssn=<c:out value='${doctor.ssn}' />">Deny</a>
                            	</c:if>
                            </td>
							<td class = "list table-ssn"><c:out value="${doctor.ssn}" /></td>
							<td class = "list table-name"><c:out value="${doctor.name}" /></td>
							<td class = "list table-date"><c:out value="${doctor.birthdate}" /></td>
							<td class = "list table-address"><c:out value="${doctor.address}" /></td>
							<td class = "list table-email"><c:out value="${doctor.email}" /></td>
							<td class = "list table-viewID"><c:out value="${doctor.doctorid}" /></td>
							<td class = "list table-viewID"><c:out value="${doctor.hospitalid}" /></td>
							<td class = "list table-viewID"><c:out value="${doctor.communityid}" /></td>
						</tr>
					</c:forEach>
					
				</tbody>

			</table>
			</div>
		</div>
	</div>
	
	
</body>
</html>
