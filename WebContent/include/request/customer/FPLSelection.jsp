<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script language="JavaScript">
     	function submitMe(form,actionURL, val){
     		document.getElementById("reqSaveOption").value = val;
           	form.action = actionURL;
            form.submit();
     	}
</script>

<form name="FPLSelectionForm" method="post">
<div class="col-lg-10 col-md-10 col-sm-10 newRequest">
	<h3 class="blue marginNone">Financial Planners</h3>
	<hr />
	<div class="checkbox">
		<label>
	    	<input type="checkbox" name="showOnlyOnlineFpl" ng-model="uRequest.showOnlineFP" ng-click="uRequest.submitRequest(uRequest);"/> Show only Online Financial Planners
	  	</label>
	</div>
	<hr />
    <table class="table table-hover">
    <thead>
    	<tr>
    		<th>Choose</th>
        	<th>Full Name</th>
        	<th>Location</th>
        	<th>Status</th>
      	</tr>
    </thead>
    <tbody id="FPLList">
    	<c:forEach var="partialFpl" items="${FPLList}">
    		<tr>
		        <td> <input type="checkbox" class="marginLten" name="fplId" value="${partialFpl.id}" /> </td>
		        <td> ${partialFpl.firstName} ${partialFpl.lastName} </td>
		        <td> ${partialFpl.location} </td>
		        <td class="successText cursorP">
		        	<c:choose>
		        		<c:when test="${partialFpl.online}">
		        			Online
		        		</c:when>
		        		<c:otherwise>
		        			Off line
		        		</c:otherwise>
		        	</c:choose>
		        </td>
			</tr>
    	</c:forEach>
    </tbody>
  	</table>
  	<input type="hidden" name="reqSaveOption" id="reqSaveOption">
  	<hr />
  	<div class="col-lg-12 col-md-12 col-sm-12 paddingNone">
  		<button class="btn btn-danger pull-right marginLten" onclick="submitMe(document.FPLSelectionForm,'initNewRequest.do','exit')">Exit</button>
  		<button class="btn btn-primary pull-right marginLten" onclick="submitMe(document.FPLSelectionForm,'saveRequest.do','Save')">Save and Exit</button>
  		<button class="btn btn-success pull-right" onclick="submitMe(document.FPLSelectionForm,'saveRequest.do', 'SendFP')">Send request</button>
	</div>
</div>
</form>