<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style><jsp:directive.include file="css-files/global_style.css" /></style>
	<style><jsp:directive.include file="css-files/loginForm_style.css" /></style>
	<title>Login</title>
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
		<div class = "login-container">
			<form id = "login" method = "post" action = "login">
				<label for = "loginUsername">Email: </label><br>
				<input type = "text" placeholder = "E-mail Address" name = "email" id = "email" required><br><br>
				<label for = "loginPassword">Password: </label><br>
				<input type = "password" placeholder = "Password" name = "password" id = "password" required>
				<br>
				<div class = "login-buttons-container">
					<button type = "submit" formmethod = "POST" id = "loginButton" class = "buttons">Login</button>
					<button type = "reset" id = "clearButton" class = "buttons">Clear</button>
				</div>
			</form>
			 <c:if test="${not empty error}">
   				<c:out value="${error}"/>
			</c:if>
		</div>
	</div>
</body>
</html>