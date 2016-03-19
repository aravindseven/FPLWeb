<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script language="JavaScript">
     	function submitProfile(form,actionURL, mailId){
     		document.getElementById('submittedEmail').value = mailId;
           	form.action = actionURL;
           	form.submit();
    	}
     	function submitMe(form,actionURL){
           	form.action = actionURL;
            form.submit();
     	}
</script>

<div class="col-lg-10 col-md-10 col-sm-10 profileTabForm">
	<h3 class="blue marginNone">Search Profile</h3>
    <hr />
    <form action="searchProfile.do" method="post" class="col-sm-12">
	    <div class="col-lg-12 col-md-12 col-sm-12">
	    	<div class="form-group col-lg-3 col-md-3 col-sm-3 paddingNone">
	        	<div>
	            	<input type="text" class="form-control" placeholder="Name" name="name" value="${searchProfile.name}"/>
	            </div>
	        </div>
	    	<div class="form-group col-lg-3 col-md-3 col-sm-3 paddingNone">
	        	<div>
	            	<input type="email" class="form-control" placeholder="Email id" name="email" value="${searchProfile.email}"/>
	            </div>
	        </div>
	    	<div class="form-group col-lg-3 col-md-3 col-sm-3 paddingNone">
	        	<div>
	            	<input type="text" class="form-control" placeholder="Phone Number" name="mobileNum" value="${searchProfile.mobileNum}"/>
	            </div>
	        </div>
	        <div class="form-group col-lg-1 col-md-1 col-sm-1">
	        	<div>
	            	<button class="btn btn-primary">Search</button>
	            </div>
	        </div>
	    </div>
    </form>
    <hr class="clear" />
    
    <c:if test="${ not empty profileSearchResult}">
	    <table class="table table-hover">
	    	<thead>
	        	<tr>
	        		<th>Id</th>
	             	<th>Name</th>
	                <th>DOB</th>
	                <th>Email ID</th>
	                <th>Phone number</th>
	                <th>User Type</th>
	                <th>Actions</th>
	            </tr>
	        </thead>
	        <tbody>
		    	<form name="adminProfileEditForm" id="adminProfileEditFormId" method="post">
			        <c:forEach var="searchView" items="${profileSearchResult}">
				        
				        	<tr>
				        		<td>${searchView.id}</td>
				            	<td>${searchView.name}</td>
				                <td>${searchView.dob}</td>
				                <td>${searchView.email}</td>
				                <td>${searchView.mobileNumber}</td>
				                <td>${searchView.userType}</td>
				                <td>
			                		<a href="#" onclick="submitProfile(document.adminProfileEditForm,'activateProfile.do','${searchView.email}')">
			                		   	<img src="support/images/activate.jpg" title="Activate" class="cursorP"/>
				                	</a>
			                		<a href="#" onclick="submitProfile(document.adminProfileEditForm,'deActivateProfile.do','${searchView.email}')">
			                		   	<img src="support/images/deactivate.jpg" class="marginLten cursorP" title="Deactivate" />
				                	</a>
			                		<a href="#" onclick="submitProfile(document.adminProfileEditForm,'editProfile.do','${searchView.email}')">
			                			<img src="support/images/editNew.png" class="marginLten cursorP" title="Edit"/>
				                	</a>
				               	</td>
				            </tr>
				        
			        </c:forEach>
			        <input type="hidden" name="submittedEmail" id="submittedEmail">
		        </form> 
			</tbody>
		</table>
    </c:if> 
</div>
