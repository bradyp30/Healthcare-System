<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/registration-lists_style.css" /></style>
	<title>Patients in my Hospital</title>
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
						<th class = "list table-ssn">SSN</th>
						<th class = "list table-name">Name</th>
						<th class = "list table-address">Address</th>
						<th class = "list table-date">Birth Date</th>
						<th class = "list table-email">E-mail</th>
						<th class = "list table-viewID">Family ID</th>
						<th class = "list table-viewID">Previous Family ID</th>
						<th class = "list table-viewID">Community ID</th>
						<th class = "list table-viewID">Doctor ID</th>
						<!-- Password (if needed) -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="patient" items="${listPatient}">
						<tr>
							
						
							<td class = "list table-ssn"><c:out value="${patient.ssn}" /></td>
							<td class = "list table-name"><c:out value="${patient.name}" /></td>
							<td class = "list table-address"><c:out value="${patient.address}" /></td>
							<td class = "list table-date"><c:out value="${patient.birthdate}" /></td>
							<td class = "list table-email"><c:out value="${patient.email}" /></td>
							<td class = "list table-viewID"><c:out value="${patient.familyid}" /></td>
							<td class = "list table-viewID"><c:out value="${patient.prevfamilyid}" /></td>
							<td class = "list table-viewID"><c:out value="${patient.communityid}" /></td>
							<td class = "list table-viewID"><c:out value="${patient.doctorid}" /></td>
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
