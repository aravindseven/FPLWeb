<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="myCustomer" class="col-lg-10 col-md-10 col-sm-10 profileTabForm alternativeForm" ng-controller="UserProfileController as uProfile">
	<form ng-init="uProfile.fetchMyCustomer()"  autocomplete="off" role="form" id="myCustomerform" class="col-sm-12">
	<div class="row">
		<div class="col-md-12 paddingNone">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<h3>MY Customer</h3>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<h3></h3>
			</div>
		</div>
		<hr class="clear">
		<table class="table table-hover">
    	<thead>
        	<tr>
                <th>Name</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="MyCustomerResult">
        	
    	</tbody>
	</table>
	</div>
	</form>
	</div>
