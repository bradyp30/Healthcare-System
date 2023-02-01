<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style><jsp:directive.include file="css-files/global_style.css" /></style>
<style><jsp:directive.include file="css-files/patient-registration_style.css" /></style>

<title>Create New Patient Test</title>
</head>
<body>

	<header>
        <nav class="navbar">
            <div id = "navbar-text">
                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
            </div>

            <ul class="navbar-nav">
                <c:if test="${user == 'admin'}">
                	<li id = "nav-login"><a href="login-form.jsp">Logout</a></li>
				</c:if>
				<c:if test="${user != 'admin'}">
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
    
    <div class = "main-body">
        <form method="post" action="insertNewTest">
    	<table>
    		<h1 style="margin-bottom: 0; margin-left: 5px"><u>Create New Patient Test</u></h1>
			<tr>
				<td>Test Name</td>
				<td>Upper Bound</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='testName' id='testName'class = "createTestForm" required/>
				</td>
				<td>
					<input type='text' name='upperBound' id='upperBound' class = "createTestForm" required/>
				</td>
			</tr>
			<!--  Start of row -->
			<tr>
				<td>Lower Bound</td>
				<td>Units</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='lowerBound' id='lowerBound' class = "createTestForm" required/>
				</td>
				<td>
					<input type='text' name='testUnits' id='testUnits' class = "createTestForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			
		</table>
    
	    <div class = "login-buttons-container">
			<button type = "submit" formmethod = "POST" id = "createTestSubmit"  class = "buttons">Create Test</button>
			<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
		</div>
	    
		</form>
    </div>

</body>
</html>