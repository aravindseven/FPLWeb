<%
	String errorMsg = (String) request.getAttribute("ErrorMessage");
	String successMsg = (String) request.getAttribute("SuccessMessage");
	if(successMsg != null && !successMsg.isEmpty()) {
		
%>
	<div>
		<hr color="green" align="center"/> 
			 <b> <%= successMsg %> </b> <br />
		<hr color="red" />
	</div>
	<br />
<% 
	} else {
		if(errorMsg == null || errorMsg.isEmpty()) {
			errorMsg = (String) request.getParameter("ErrorMessage");
			if(errorMsg == null || errorMsg.isEmpty()) {
				errorMsg = "Your session has been expred!!";
			}
		}
%>
	<div align="center">
		<hr color="red" align="center" /> 
			 <b> <%= errorMsg %> </b> <br />
		<hr color="red" />
	</div>
	<br />
<%
	}
%>