<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- MY PROFILE START -->

<div class="container-fluid" style="background:#e9eef1; padding:px;" ng-controller="TipsController as uTips" ng-init="uTips.getalltips()">
		
		<div class="row">
			<button type= "submit" id="save" data-toggle="modal" ng-click="uTips.addmodal()">New Tip</button>
		</div>
		<div class="row">
			<table style="width:100%">
		  <tr>
		    <td>S No</td>
		    <td>Name</td> 
		  </tr>
		  <tr ng-repeat="element in tipslist">
		  	<td>{{$index+1}}</td> 
		    <td ng-click="uTips.editmodal(element.Tip_Name)">{{element.Tip_Name}}</td>
		  </tr>
		</table>
		</div>
		<!-- Trigger the modal with a button -->
	
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Add New Tip</h4>
	      </div>
	      <div class="modal-body">
	        	<form action="#" role="form" id="form" class="col-sm-12">
                <div class="row">
                  <div class="col-md-6">
                    <div>
                      <div class="form-group">
                        <label class="control-label" >Tip Name</label>
                        <input type="text" number="false"  ng-model="tip_name"class="form-control">
                      </div>
                      <div class="form-group">
                        <label class="control-label" >Description</label>
                        <textarea rows="5" cols="50" ng-model="tip_description"></textarea>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-default" ng-click="uTips.savetip()">Save</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	
	<!-- EDIT MODAL -->
	<div id="myModal1" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">{{dataobj.Tip_Name}}</h4>
	      </div>
	      <div class="modal-body">
	        	<form action="#" role="form" id="form" class="col-sm-12">
                <div class="row">
                  <div class="col-md-6">
                    <div>
                      <div class="form-group">
                        <label class="control-label" >Description</label>
                        <textarea rows="5" cols="50" ng-model="dataobj.Tip_Description"></textarea>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-default" ng-click="uTips.updatetip()">Update</button>
	      	<button type="button" class="btn btn-default" ng-click="uTips.deletetip()">Delete(Tip)</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	</div>

<!-- MY PROFILE END -->