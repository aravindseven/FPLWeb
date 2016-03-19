<div class="left-content hidden-xs hidden-sm">
		<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
			<div class="nav nav-pills nav-stacked">
				<div class="mail-box">		
					<div class="search">
						<div class="input-group stylish-input-group">
							<input type="text" class="form-control" placeholder="Search" ng-model="dataobj.search" style="border-left:none;" ></input>
								<span class="input-group-addon">
									<button type="submit" ng-click="uCommunication.searchmail()" style='border:none;background:none;border-right:none;'>
										<span class="glyphicon glyphicon-search" style='background:none;'></span>
									</button>  
								</span>
						</div>
					</div>					
									
						
					<div class="sub_menu">
						<ul class="list">
							<li  id="compose"><a href="?type=compose" ><i class="fa fa-pencil-square-o"><span class="back cust_active_color">Compose</span></i></a></li>
							<li id="inbox"><a href="?type=inbox" ng-click="uCommunication.closeMail();uCommunication.inboxfrom(1)"><i class="fa fa-envelope-o"><span class="back ">Inbox</span></i></a></li>
							<li id="sent"><a href="?type=sent" ng-click="uCommunication.closeMail();uCommunication.sentfrom(1)" ><i class="fa fa-share-square"><span class="back ">Sent</span></i></a></li>
							<li id="draft"><a href="?type=draft" ng-click="uCommunication.closeMail();uCommunication.draft(1)"><i class="fa fa-file-text-o"><span class="back ">Draft</span></i></a></li>
							<li id="archive"><a href="?type=archive" ng-click="uCommunication.closeMail();uCommunication.archivefrom(1)" ><i class="fa fa-archive"><span class="back ">Archive</span></i></a></li>
							<li id="trash"><a href="?type=trash" ng-click="uCommunication.closeMail();uCommunication.trashfrom(1)" ><i class="fa fa-trash"><span class="back ">Trash</span></i></a></li>
							<li id="notification"><a href="?type=notification" ng-click="uCommunication.closeMail();uCommunication.notification(1)" ><i class="fa fa-trash"><span class="back ">Notification</span></i></a></li>
							<li id="settings"><a href="?type=Settings" ><i class="fa fa-cog"><span class="back cust_active_color">Settings</span></i></a></li>
						</ul>
					</div>
					
				</div>	
				<div class="tips">
						
						<div class="tip"><i class="fa fa-info-circle"><span class="tip_text">TIPS</span></i><i class="fa fa-caret-right"></i></div>
								
<div class="tip" style="padding-top:5px;">Introducing Brandalf, Your Ultimate Brand GuruIntroducing Brandalf, Your Ultimate Brand GuruIntroducing Brandalf</div>
<!-- tip slide -----------

					<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width:500px; height: 500px; visibility: hidden;">
        
				        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden;">
				            <div data-p="112.50" style="display: none;">
				               
				                <div data-u="thumb" ng-repeat = "element in tipslist">{{element.Tip_Description}}</div>
				            </div>
				            
				        </div>
				        Thumbnail Navigator
				        <div data-u="thumbnavigator" class="jssort09-600-45" style="position:absolute;bottom:0px;left:0px;width:600px;height:300px;">
				            <div style="position: absolute; top: 0; left: 0; width: 100%; height:100%; background-color: red; filter:alpha(opacity=40.0); opacity:1;"></div>
				            Thumbnail Item Skin Begin
				            <div data-u="slides" style="cursor: default;">
				                <div data-u="prototype" class="p">
				                    <div data-u="thumbnailtemplate" class="t"></div>
				                </div>
				            </div>
				            Thumbnail Item Skin End
				        </div>
				        
				    </div>			 -->			
				</div>
					
			</div>
		</div>
</div>





<!-- <div class="nav nav-pills nav-stacked col-md-2 col-lg-2 col-sm-3 col-xs-12">

	<div class="search">
		<div class="input-group stylish-input-group">
			<input type="text" class="form-control" placeholder="Search" >
			<span class="input-group-addon">
				<button type="submit" style='border:none;background:none'>
					<span class="glyphicon glyphicon-search" style='background:none'></span>
				</button>  
			</span>
		</div>
	</div>					
	<div class="sub_menu">
		<ul class="list">
			<li class="active"><a href="?type=compose"><span class="back">Compose</span></a></li>
			<li><a href="?type=inbox" ng-click="uCommunication.closeMail();uCommunication.inbox(1)"><span class="back1">Inbox</span></a></li>
			<li><a href="?type=sent" ng-click="uCommunication.closeMail();uCommunication.inbox(1)"><span class="back2" style="padding-left:33px;">Sent</span></a></li>
			<li><a href="?type=draft"><span class="back3">Draft</span></a></li>
			<li><a href="?type=archive"><span class="back4">Archive</span></a></li>
			<li><a href="?type=trash"><span class="back5">Trash</span></a></li>
			<li><a href="?type=Settings"><span class="back7">Setting</span></a></li>
		</ul>
	</div>
	<div class="col-lg-12">
		<div class="row" style="background:white;">
			<div class="col-lg-12 tips">
				<div class="tip"><img src="support/communication/images/q.png"  alt=""/><span class="tip_text">TIPS</span><span class="tip_imga"><img src="support/communication/images/ae.png"/></span></div>
				<div class="tip tip_slide_container" >

tip slide -----------
					<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width:500px; height: 500px; visibility: hidden;">
        
				        <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden;">
				            <div data-p="112.50" style="display: none;">
				               
				                <div data-u="thumb" ng-repeat = "element in tipslist">{{element.Tip_Description}}</div>
				            </div>
				            
				        </div>
				        Thumbnail Navigator
				        <div data-u="thumbnavigator" class="jssort09-600-45" style="position:absolute;bottom:0px;left:0px;width:600px;height:300px;">
				            <div style="position: absolute; top: 0; left: 0; width: 100%; height:100%; background-color: red; filter:alpha(opacity=40.0); opacity:1;"></div>
				            Thumbnail Item Skin Begin
				            <div data-u="slides" style="cursor: default;">
				                <div data-u="prototype" class="p">
				                    <div data-u="thumbnailtemplate" class="t"></div>
				                </div>
				            </div>
				            Thumbnail Item Skin End
				        </div>
				        
				    </div>
end of tip slide-----------

				</div>						
			</div>
		</div>
	</div>
</div> -->