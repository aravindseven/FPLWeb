<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-10 col-md-10 col-sm-10 requestGrid" ng-controller="AdminSubscriptionController as aSubscription">
<form id="AdvList" ng-init="aSubscription.fetchAdvTypes();">
<table class="table table-hover">
    	<thead>
        	<tr>
            	<th>Types Name</th>
                <th>Subscription Rate</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="listAdv">
        	<tr>
		        <td></td>
		        <td></td>
		        <td></td>
		        <td></td>
		        <td></td>
		    </tr> 
    	</tbody>
	</table>
	</form>
</div>	