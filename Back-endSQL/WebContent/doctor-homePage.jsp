<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style><jsp:directive.include file="css-files/global_style.css" /></style>
		<style><jsp:directive.include file="css-files/doctor-homepage_style.css" /></style>	
		<title>Doctor Home Page</title>
	</head>
	<body>
		<header>
	        <nav class="navbar">
	            <div id = "navbar-text">
	                <a href="" class="navbar-homeName">Remote Patient Monitoring System</a>
	            </div>
	            <ul class="navbar-nav">
		            <c:if test="${user == 'doctor'}">
		            	<li><a href="<%=request.getContextPath()%>/listAbnormalData">My Patients</a></li>
		            	<li id = "doctorDrop">
	                	<div class = "dropdown">
	                		<a>Appointments</a>
	                		<div class  = "dropdownContent doctorDropdown">
	                			<a href="doctor-appointments.jsp" class = "dropOption">Create New Appointment</a>
	                			<a href="<%=request.getContextPath()%>/listDoctorAppointments">My Appointments</a>
	                		</div>
	                	</div>
	               		</li>
		            </c:if>
	                <c:if test="${user == 'doctor'}">
	                	<li id = "nav-login"><a href="login-form.jsp">Logout</a></li>
					</c:if>
					<c:if test="${user != 'doctor'}">
	                	<li id = "nav-login"><a href="login-form.jsp">Login</a></li>
					</c:if>
	                
	                <li id = "nav-register">
	                
	                	<div class = "dropdown">
	                		<a>Register</a>
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
			<form method="post" action="doctorGraphChoice">
				<div class="patientSSN-prompt">
					<h1>Enter Specific Patient SSN:</h1>
			   		<div id="docFindPatientSSN">
						
						<input type='text' name='searchPatientSSN' id='searchPatientSSN' class = "ssnSearch" required/>
						<div class = "testName-dropdown">
							<select name="testName" id="testName" required> <!-- Adding dropdown menu to select graph -->
								<option value="" selected disabled hidden = "true" class = "default">Select Vital</option>
							  	<option value="Blood Pressure">Blood Pressure</option>
							  	<option value="Pulse">Pulse</option>
							  	<option value="Temperature">Temperature</option>
							  	<option value="Oxygen">SpO2</option>
							  	<option value="Glucose">Glucose</option>
							  	<option value="Weight">Weight</option>
							</select>
						</div>
						<button type="submit" formmethod="POST" id="doctorSubmitPatientSSN"  class ="buttons">Search</button>
						<button type="reset" class ="buttons">Clear</button>
					</div>
				</div>
			</form>
		
		<div class="row">
			<div class="container">
			
			<form method="post" action="listAbnormalData">
				<h1 class = "header-margin">Abnormal Patient Vitals:</h1>
				<div id = "docFindPatientSSN">
					
					<button type="submit" formmethod="POST" id="doctorSubmitTestName" class ="buttons">Reload</button>
				</div>
				</form>
				<div class = "table-container" style="overflow-x:scroll;">

				<table class = "list-table">
					<thead>
						<tr>
							<th class = "list table-date">Date</th>
							<th class = "list table-name">Name</th>
							<th class = "list table-ssn">SSN</th>
							<th class = "list table-concern">Test</th>
						</tr>
					</thead>
					<c:forEach var="abnormal" items="${listAbnormal}">
							<tbody>
								<tr>										
									<td class = "list table-date"><c:out value="${abnormal.date}" /></td>
									<td class = "list table-name"><c:out value="${abnormal.name}" /></td>
								    <td class = "list table-ssn"><c:out value="${abnormal.ssn}" /></td>
									<td class = "list table-concern"><c:out value="${abnormal.test}" /></td>
								</tr>
							</tbody>
						</c:forEach>
					
					
				
					</table>

				</div>
			</div>
		</div>
		</div>
	<script>
		var x, i, j, l, ll, selElmnt, a, b, c;

		x = document.getElementsByClassName("testName-dropdown");
		l = x.length;
		for (i = 0; i < l; i++) {
		  selElmnt = x[i].getElementsByTagName("select")[0];
		  ll = selElmnt.length;

		  a = document.createElement("DIV");
		  a.setAttribute("class", "option-selected");
		  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
		  x[i].appendChild(a);

		  b = document.createElement("DIV");
		  b.setAttribute("class", "select-option option-hide");
		  for (j = 1; j < ll; j++) {


		    c = document.createElement("DIV");
		    c.innerHTML = selElmnt.options[j].innerHTML;
		    c.addEventListener("click", function(e) {


		        var y, i, k, s, h, sl, yl;
		        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
		        sl = s.length;
		        h = this.parentNode.previousSibling;
		        for (i = 0; i < sl; i++) {
		          if (s.options[i].innerHTML == this.innerHTML) {
		            s.selectedIndex = i;
		            h.innerHTML = this.innerHTML;
		            y = this.parentNode.getElementsByClassName("currently-selected");
		            yl = y.length;
		            for (k = 0; k < yl; k++) {
		              y[k].removeAttribute("class");
		            }
		            this.setAttribute("class", "currently-selected");
		            break;
		          }
		        }
		        h.click();
		    });
		    b.appendChild(c);
		  }
		  x[i].appendChild(b);
		  a.addEventListener("click", function(e) {


		    e.stopPropagation();
		    closeAllSelect(this);
		    this.nextSibling.classList.toggle("option-hide");
		    this.classList.toggle("select-option-active");
		  });
		}
	
		function closeAllSelect(elmnt) {


		  var x, y, i, xl, yl, arrNo = [];
		  x = document.getElementsByClassName("select-option");
		  y = document.getElementsByClassName("currently-selected");
		  xl = x.length;
		  yl = y.length;
		  for (i = 0; i < yl; i++) {
		    if (elmnt == y[i]) {
		      arrNo.push(i)
		    } else {
		      y[i].classList.remove("select-option-active");
		    }
		  }
		  for (i = 0; i < xl; i++) {
		    if (arrNo.indexOf(i)) {
		      x[i].classList.add("option-hide");
		    }
		  }
		}
	


		document.addEventListener("click", closeAllSelect);
		
	</script>
	</body>
</html>