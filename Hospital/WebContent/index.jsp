
<%@page import="model.LoginSelect"%>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%	
	if (request.getParameter("userName") == null) {		
	}else{
		if(request.getParameter("loginVal") == ""){
			LoginSelect loginSelect = new LoginSelect();
			String s1 = request.getParameter("userName");
			String s2 = request.getParameter("password");
			System.out.println("Log in attributes : "+s1+" "+s2);
			
			if(loginSelect.loginVal(s1, s2) == 1){
				response.sendRedirect("http://localhost:8085/Hospital/Hospital_Insert.jsp");
			}else if(loginSelect.loginVal(s1, s2) == 2){
				response.sendRedirect("http://localhost:8090/Doctor/Doctor_Insert.jsp");
			
			}else if(loginSelect.loginVal(s1, s2) == 3){
				response.sendRedirect("http://localhost:8095/Patient/Patient_Insert.jsp");
			
			}else if(loginSelect.loginVal(s1, s2) == 4){
				response.sendRedirect("http://localhost:8080/Appointment/Appointment_Insert.jsp");
			
			}else if(loginSelect.loginVal(s1, s2) == 5){
				response.sendRedirect("http://localhost:9090/Payment/Payment_Insert.jsp");
					
			}else{
				response.sendRedirect("index.jsp");
				System.out.println("Login failed..............");
			}
			
		}
	}
    
%>	
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 
<form id="loginform" name="loginform" method="post" action="index.jsp">
		UserName: 
		<input id="userName" name="userName" type="text" class="form-control form-control-sm"> <br>
	    Password:
	    <input id="password" name="password" type="text" class="form-control form-control-sm"> <br> 
		<input id="btnLogin" name="btnLogin" type="submit" value="Login" class="btn btn-primary">
		<input type="hidden" id="loginVal" name="loginVal" value="" > 		
</form>

</body>
</html>