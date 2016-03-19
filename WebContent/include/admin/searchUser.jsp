<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-10 col-md-10 col-sm-10 profileTabForm" ng-controller="AdminProfileManagementController as uProfile">
		<form role="form" id="userForm" class="col-sm-12">
			 <div class="row">
				<div class="col-md-12 paddingNone">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                    	<h3>Search User</h3>
                    </div>	
								<div class="col-md-12 col-sm-12">
								<table class="table table-striped" id="searchRequestTable">
    								<tbody>
        								<tr>
	  									 <td>
											<div class="form-group col-lg-6 col-md-6 col-sm-6 paddingNone">
												<div>
													<input type="text" class="form-control" placeholder="Name" ng-model="searchuser.name"/>
												</div>
											</div>
										</td>	
										<td>
											<div class="form-group col-lg-6 col-md-6 col-sm-6 paddingNone marginLten">
												<div>
													<input type="email" class="form-control"
														placeholder="Email Id" ng-model="searchuser.email"/>
												</div>
											</div>
										</td>
										<td>	
											<div
												class="form-group col-lg-6 col-md-6 col-sm-6 paddingNone marginLten">
												<div>
													<input type="text" class="form-control"
														placeholder="Phone Number" ng-model="searchuser.phone"/>
												</div>
											</div>
										</td>
										<td>
											<div class="form-group col-lg-6 col-md-6 col-sm-6">
												<div>
													<button type="button" class="btn btn-primary" ng-click="uProfile.searchUser(searchuser)">Search</button>
												</div>
											</div>
										</td>	
									</div>
									</tr>
								</tbody>
								</table>
								
								<hr class="clear" />
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Name</th>
											<th>DOB</th>
											<th>Email ID</th>
											<th>Phone number</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody ng-repeat="user in userList">
										<tr>
											<td>{{user.name}}</td>
											<td>{{user.dob}}</td>
											<td>{{user.emailid}}</td>
											<td>{{user.phonenumber}}</td>
											<td class="profileEdit"><img
												src="support/images/activate.jpg" title="Activate"
												ng-click="uProfile.activateAccount(user.emailid)" class="cursorP" /> <img
												ng-click="uProfile.deactivateAccount(user.emailid)" src="support/images/deactivate.jpg"
												class="marginLten cursorP" title="Deactivate" /> <img
												src="support/images/editNew.png"
												ng-click="uProfile.editUser(user.emailid)" class="marginLten cursorP" title="Edit" /></td>
										</tr>
									</tbody>
								</table>
								<!-- <div ng-show="{{userList.length != 0}}">Records Found: </div>
								<div ng-show="{{userList.length == 0}}">No Records Found.</div> -->
							</div>
						</div>
					</div>
