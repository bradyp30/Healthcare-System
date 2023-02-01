<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/community-registration_style.css" /></style>
	<title>Community Member Registration</title>
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
        <form method="post" action="insertCommunityOwner">
    	<table>
    		<h1 style="margin-bottom: 0; margin-left: 5px; text-decoration: underline;">Community Member Registration</h1>
			<!--  Start of row -->
			<tr>
				<td>E-mail Address</td>
				<td>Password</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='commEmail' id='commEmail' class = "commRegForm" required/>
				</td>
				<td>
					<input type='password' name='commPassword' id='commPassword' class = "commRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->
			<!--  Start of row -->
			<tr>
				<td>Community ID</td>
			</tr>
			<tr>
				<td>
					<input type='text' name='commID' id='commID' class = "commRegForm" required/>
				</td>
			</tr>
			<!--  End of row -->


		</table>

	    <div class = "login-buttons-container">
			<button type = "submit" formmethod = "POST" id = "commRegisterSubmit"  class = "buttons">Register</button>
			<button type = "reset" id = "clearButton"  class = "buttons">Clear</button>
		</div>

		</form>
    </div>
</body>
</html>