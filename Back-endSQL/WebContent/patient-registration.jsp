<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/patient-registration_style.css" /></style>
<title>Patient Registration</title>
<!-- 
Form created by Tyler 2021-04-06 Sent what I had to Karen and she helped motify file aswell (had to change this)
--> 

<!--  Test Commit #1-->

	


</head>
<body>

	<header>
        <nav class="navbar">
            <div id = "navbar-text">
                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
            </div>

            <ul class="navbar-nav">
                <li id = "nav-login"><a href="login-form.jsp">Login</a></li>
                
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
        <form method="post" action="insertPatient">
    	<table>
    		<h1 style="margin-bottom: 0; margin-left: 5px; text-decoration: underline;">Patient Registration</h1>
			<tr>
				<td>Full Name</td>
				<td>Social Security Number (SSN)</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='patientName' id='patientName'class = "patientRegForm" required/>
				</td>
				<td>
					<input type='text' name='patientSSN' id='patientSSN' class = "patientRegForm" required/>
				</td>
			</tr>
			
			<!--  Start of row -->
			<tr>
				<td class = "patientAdd" colspan = "2">Current Address</td>
			</tr>
			<tr>
				<td class = "patientAdd" colspan = "2">
					<input type='text' name='patientAddress' id='patientAddress' class = "patientRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			
			<!--  Start of row -->
			<tr>
				<td>E-mail Address</td>
				<td>Password</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='patientEmail' id='patientEmail' class = "patientRegForm" required/>
				</td>
				<td>
					<input type='password' name='patientPassword' id='patientPassword' class = "patientRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			
			<!--  Start of row -->
			<tr>
				<td>Family ID</td>
				<td>Previous Family ID</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='patientFamilyID' id='patientFamilyID' class = "patientRegForm" required/>
				</td>
				<td>
					<input type='text' name='patientPrevFamilyID' id='patientPrevFamilyID' class = "patientRegForm"/>
				</td>
			</tr>
			<!--  End of row -->
			
			<!--  Start of row -->
			<tr>
				<td>Birthday</td>
				<td>Community ID</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='patientBirthday' id='patientBirthday' class = "patientRegForm" required/>
				</td>
				<td>
					<input type='text' name='patientCommID' id='patientCommID' class = "patientRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			
			<!--  Start of row -->
			<tr >
				<td>Doctor ID</td>
				
			</tr>
			<tr>
				<td>
					<input type='text' name='patientDocID' id='patientDocID' class = "patientRegForm" required/>
				</td>
				
			</tr>
			<!--  End of row -->
			
		</table>
    
	    <div class = "login-buttons-container">
			<button type = "submit" formmethod = "POST" id = "patientRegisterSubmit"  class = "buttons">Register</button>
			<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
		</div>
	    
		</form>
    </div>

</body>
</html>
