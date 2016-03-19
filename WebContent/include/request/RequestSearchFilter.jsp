<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form action="SearchRequest.do" name="CustSearchRequestForm" id="CustSearchRequestFormId" class="col-sm-12" method="post">
<div class="form-horizontal">
	 <div class="form-group">
	 <table class="table table-striped" id="searchRequestTable">
    	<tbody>
        	<tr>
	  			 <td>
	  			 	<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<input type="text" class="form-control input-medium search-query" title="Request ID" id="requestId" name="requestId" placeholder="Request ID" value="${SearchRequestParam.requestId}" />
	  			 	</div>
	  			 </td>
	  			 <td>
	  			 	<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<input type="date" class="form-control input-medium search-query" title="From date" id="fromDate" name="fromDate" placeholder="From Date" value="${SearchRequestParam.fromDate}" />
	    			</div>
	    		</td>
	    		<td>
	  			 	<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<input type="date" class="form-control input-medium search-query" title="To date" id="toDate" name="toDate" placeholder="To Date" value="${SearchRequestParam.toDate}" />
	    			</div>
	    		</td>	 
	    		<td>
	    			<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<select class="form-control" name="type" title="Type">
	    					<option value="">--Select--</option>
		    				<c:forEach var="domain" items="${DomainList}">
		    					<fmt:parseNumber var="selectedDomain" type="number" value="${SearchRequestParam.type}" />
		    					<c:choose>
		    						<c:when test="${domain.id eq selectedDomain}">
		    							<option value="${domain.id}" selected="selected">${domain.name}</option>
		    						</c:when>
		    						<c:otherwise>
		    							<option value="${domain.id}">${domain.name}</option>
		    						</c:otherwise>
		    					</c:choose>
		    					
		    				</c:forEach>
	    				</select>
	    			</div>
	    		</td>	
	    		<td>
	    			<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<select class="form-control" name="status" title="Status">
	    					<option value="">--Select--</option>
		    				<c:forEach var="statusView" items="${ReqestStatuList}">
		    					<fmt:parseNumber var="selectedStatus" type="number" value="${SearchRequestParam.status}" />
		    					<c:choose>
		    						<c:when test="${statusView.id eq selectedStatus}">
		    							<option value="${statusView.id}" selected="selected">${statusView.description}</option>
		    						</c:when>
		    						<c:otherwise>
		    							<option value="${statusView.id}">${statusView.description}</option>
		    						</c:otherwise>
		    					</c:choose>
		    					
		    				</c:forEach>
	    				</select>
	    			</div>
	    		</td>		    		
	    		<td>
	    			<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<button type="submit" id="searchRequest" class="btn btn-primary">Search Request</button>
	    			</div>
	    		</td>	
	    	</tr>
	    </tbody>
	   </table>	   
	 </div>	
</div>	    	
</form>