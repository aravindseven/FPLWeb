<div class="modal fade" id="FPLRespondModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog registerUser">  
		<form name="FPLRespondForm"  autocomplete="off" novalidate ng-submit="uRequest.respond(uRequest)">		
			<div class="modal-content loginWrapper">
				<div class="modal-header">
					<div>Response</div>
				</div>
				<div class="modal-body">				
					<div class="col-lg-10 col-md-10 col-sm-10">
              			<div class="form-group">
				          		<textarea type="text" rows="7" cols="20" class="form-control" id="respondDescription" name="respondDescription" ng-model="uRequest.respondDescription" placeholder="Type your Response Here."></textarea>
				      	</div>	
				      	<div>
							<button type="submit" class="btn btn-primary">Submit</button>
							<button data-dismiss="modal" class="btn btn-primary" type="button" ng-click="uRequest.reset()">Cancel</button>
						</div>	               
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