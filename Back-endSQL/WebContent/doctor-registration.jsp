<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
        <form method="post" action="insertDoctor">
    	<table>
    		<h1 style="margin-bottom: 0; margin-left: 5px; text-decoration: underline;">Doctor Registration</h1>
			<tr>
				<td>Full Name</td>
				<td>Social Security Number (SSN)</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='doctorName' id='doctorName'class = "doctorRegForm" required/>
				</td>
				<td>
					<input type='text' name='doctorSSN' id='doctorSSN'class = "doctorRegForm" required/>
				</td>
			</tr>
			
			<!--  Start of row -->
			<tr>
				
				<td colspan = "2">Current Address</td>
			</tr>
			<tr>
				<td colspan = "2">
					<input type='text' name='doctorAddress' id='doctorAddress' class = "doctorRegForm" required/>
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
					<input type='text' name='doctorEmail' id='doctorEmail' class = "doctorRegForm" required/>
				</td>
				<td>
					<input type='password' name='doctorPassword' id='doctorPassword' class = "doctorRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			
			
			<!--  Start of row -->
			<tr>
				<td>Birthday</td>
				<td>Hospital ID</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='doctorBirthday' id='doctorBirthday' class = "doctorRegForm" required/>
				</td>
				<td>
					<input type='text' name='doctorHospID' id='doctorHospID' class = "doctorRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			
			
			
			<!--  Start of row -->
			<tr>
				<td>Doctor ID</td>
				<td>Community ID</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='doctorID' id='doctorID' class = "doctorRegForm" required/>
				</td>
				<td>
					<input type='text' name='doctorCommID' id='doctorCommID' class = "doctorRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->

			
		</table>
    
	    <div class = "login-buttons-container">
			<button type = "submit" formmethod = "POST" id = "doctorRegisterSubmit"  class = "buttons">Register</button>
			<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
		</div>
	    
		</form>
    </div>
</body>
</html>