<div class="modal fade" id="ViewRequestModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog registerUser">  
		<form name="viewreguestForm"  autocomplete="off">
			<div class="modal-content loginWrapper">
				<div class="modal-header">
					<div>Request</div>
				</div>
				<div id="viewModalBody" class="modal-body">				
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Request ID:</label>{{uRequest.viewId}}				 						
					</div>				
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Requested Domain Name:</label>{{uRequest.domainName}}					 						
					</div>	
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Request Location:</label>{{uRequest.location}}					 						
					</div>							
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Request Description:</label>{{uRequest.description}}					 						
					</div>						
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Request Created Date:</label>{{uRequest.createdDate}}					 						
					</div>	
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Request Updated Date:</label>{{uRequest.updatedDate}}					 						
					</div>	
					<div class="col-lg-6 col-md-6 col-sm-6">
						<label class="control-label">Request Status:</label>{{uRequest.currentStatus}}					 						
					</div>
				</div>				
				<div class="modal-footer">
					<span class="logo"></span>
					<span class="pull-right">Copyright fplonline 2014</span>
				</div>
			</div>
		</form>
	</div>
</div>