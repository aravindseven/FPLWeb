<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-10 col-md-10 col-sm-10 requestGrid" ng-controller="AdminSubscriptionController as aSubscription">
<form id="FPSubList"  ng-init="aSubscription.fetchFPSubscription();">
<table class="table table-hover">
    	<thead>
        	<tr>
            	<th>Name</th>
                <th>Contact Name</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="listFPSub">
        	<tr>
		        <td></td>
		        <td></td>
		        <td></td>
		        <td></td>
		    </tr> 
    	</tbody>
	</table>
	</form>
</div>	