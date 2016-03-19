<section class="content">
<div  class="container " ng-init="erecords.loadpolicylist();"> 
<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12col-xs-12 reqmain">
<form class="searchform" id="sandbox-container" style="padding-left:7%;">
<div class="col-lg-2 col-md-2 col-sm-12"><input type="text" placeholder="E-record ID"></div>  
<div class="col-lg-2 col-md-2 col-sm-6"><div class="input-daterange input-group" id="datepicker" style="width:100%;">
    <input class="input-sm form-control" name="start" type="text" placeholder="Start Date"></div></div> 
<div class="col-lg-2 col-md-2 col-sm-6"><div class="input-daterange input-group" id="datepicker" style="width:100%;">
    <input class="input-sm form-control" name="start" type="text" placeholder="End Date"></div></div> 
<div class="col-lg-2 col-md-2 col-sm-12"><input type="text" placeholder="Category"></div> 
<!-- <div class="col-lg-2 col-md-2 col-sm-12"><input type="text" placeholder="Status"></div>  -->
<div class="col-lg-2 col-md-2 col-sm-12 input-group-btn"><button class="btn btn-default serchiconbut" type="button"><i class="glyphicon glyphicon-search"></i> Search</button></div> 
</form>
       <a href="?type=form"><button class="round_but" type="button"><img src="images/searchreq2.png"></button></a>

   <!-- center bar -->
      <div class="reqmainsub col-lg-12 col-md-12 col-sm-12 col-xs-12" >
       <div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 Shorttablecont">   
       <div class="table-responsive"  style="width:80%;margin:0 auto;">   
                                <table class="table table-striped table-hover shorttable_left">
                                    <thead class="red searchtabhead ">
                                        <tr>
                                            <th>Date <span  class="sorttab"><i class="fa fa-sort"></i></span></th>
                                            <th>E-record ID <span  class="sorttab"><i class="fa fa-sort"></i></span></th>
                                            <th>Category</th>
                                            <th>Renewal Date</th>
                                            <th style="text-align:center;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody ng-repeat="policy in policyList" ng-cloak>
                                        <tr>
                                            <td>{{policy.policyDate}}</td>
                                            <td>RQ001</td>
                                            <td>
                                         {{policy.type}}
                                            </td>
                                            <td>
                                           Active
                                            </td>
                                            <td>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                             <div class="actioniconsing">
                                          <a href="search_view.html" class="activeview" type="button"><i class="fa fa-eye"></i></a>
                                            <a href="search_view_edit.html" class="activeview"><i class="fa fa-pencil-square-o"></i></a>
                                          <a href="" class="sweet-14 activeview"><i class="fa fa-trash-o"></i></a>
                                              </div>
                                              </div>
                                              </td>
                                        </tr>
                                        <!-- <tr>
                                            <td>05 Nov 2015</td>
                                            <td>RQ002</td>
                                             <td>
                                            
                                            Inv Linked Ins
                                            </td>
                                            <td>
                                          Active
                                            </td>
                                            <td>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                             <div class="actioniconsing">
                                          <a href="#" class="activeview"><i class="fa fa-eye"></i></a>
                                            <a href="#" class="activeview"><i class="fa fa-pencil-square-o"></i></a>
                                          <a href="#" class="activeview"><i class="fa fa-trash-o"></i></a>
                                              </div>
                                              </div>
                                              </td>
                                        </tr>
                                        <tr>
                                            <td>05 Aug 2015</td>
                                            <td>RQ003</td>
                                              <td>
                                           
                                          Life Ins
                                            </td>
                                            <td>
                                           Active
                                           </td>
                                           <td>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                             <div class="actioniconsing">
                                           <a href="#" class="activeview"><i class="fa fa-eye"></i></a>
                                            <a href="#" class="activeview"><i class="fa fa-pencil-square-o"></i></a>
                                          <a href="#" class="activeview"><i class="fa fa-trash-o"></i></a>
                                              </div>
                                              </div>
                                              </td>
                                        </tr> -->
                                    </tbody>
                                </table>
                            </div>
                            </div>
                            
                            
                           <!--  <div class="row" >
				        <div class="col-lg-12 fontsizes"  >
					<div class="row">
						<div class="col-lg-3 subclass">
						<div class="col-lg-5 subclass">
							<div class="subject"><a href="#"  ng-cloak>{{policy.type}}</a></div>
						</div>
						
						
					</div>
				</div>
			</div>
			</div> -->
       <!-- </div>-->
        </div>
        <!-- content end -->
        </section>
	<!-- tap menu start -->

    <!-- </div>
    </div> -->
 <section class="pagenav"></section>