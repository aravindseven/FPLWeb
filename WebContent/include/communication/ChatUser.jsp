<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="right-content hidden-xs hidden-sm" ng-init="uCommunication.getchatroomsfunc();uCommunication.sessiontimeoutfunc()">
	<div class=  "col-lg-2 col-md-3 col-sm-12 col-xs-12"> 
	<div class="pad">

		<div class="input-group stylish-input-group"
			style="border-right: 1px solid #ccc;">
			<input class="form-control" placeholder="Search" ng-model="userSearch.firstName"type="text">
			<span class="input-group-addon">
				<button type="submit" style="border: none; background: none">
					<span class="glyphicon glyphicon-search" style="background: none"></span>
				</button>
			</span>
		</div>
<!-- Active connections array -->
			<div class="main_chat_cont">
		 <div ng-show="active_connectedrooms=='true'">
			<h5 style="padding-left: 5px;">Active users</h5>
			<ul id="userList" ng-repeat="users in activeconnections | filter:userSearch | orderBy: 'firstName'" id="{{users.loginId}}" ng-click="uCommunication.activeconnect(users)">
				<div class="slidebelow mobileblue" >
					<div class="othersslide">
						<i class="fa fa-envelope"></i>
						<i class="fa fa-phone"></i>
					</div>
					<li class="chat_main_list" data-id="{{users.loginId}}"><img
						class="profile_pic_main" src="imgs/profilepicleft.png" />
						<ul>
							<li class="licont bold"><div>{{users.firstName}}</div></li>
							<li class="licont"><div>{{users.mood_status}}</div></li>
							<li class="licont" ng-if="users.status=='1'"><div class="online_stat c_online"></div></li>
							<li class="licont" ng-if="users.status=='3'"><div class="online_stat c_offline"></div></li>
							<li class="licont" ng-if="users.status=='4'"><div class="online_stat c_away"></div></li>
							<li class="licont" ng-if="users.status=='2'"><div class="online_stat c_busy"></div></li>
							<li class="licont" ng-if="users.status=='5'"><div class="online_stat c_dontdisturb"></div></li>
							
						</ul>
					</li>
				</div>
			</ul>
			</div> 
			<ul id="userList" ng-repeat="users in onlineusers | filter:userSearch | orderBy: 'status' "  ng-click="uCommunication.createchatroom(users,$index)">
				<div class="slidebelow mobileblue" >
					<div class="othersslide">
						<i class="fa fa-envelope"></i>
						<i class="fa fa-phone"></i>
					</div>
					<li class="chat_main_list" id="{{users.loginId}}" data-id="{{users.loginId}}"><img
						class="profile_pic_main" src="imgs/profilepicleft.png" />
						<ul>
							<li class="licont bold"><div>{{users.firstName}}</div></li>
							<li class="licont"><div>{{users.mood_status}}</div></li>
							<li class="licont" ng-if="users.status=='1'"><div class="online_stat c_online"></div></li>
							<li class="licont" ng-if="users.status=='3'"><div class="online_stat c_offline"></div></li>
							<li class="licont" ng-if="users.status=='4'"><div class="online_stat c_away"></div></li>
							<li class="licont" ng-if="users.status=='2'"><div class="online_stat c_busy"></div></li>
							<li class="licont" ng-if="users.status=='5'"><div class="online_stat c_dontdisturb"></div></li>
							
						</ul>
					</li>
				</div>
			</ul>
		</div>
	</div>

	<!-- mobile decvierf -->
	<!-- mobei end -->
	<!-- mobile start -->
	<!-- tooltip code start -->
	<!--Menu Data-->
	<div style="display: none;">
		<div id="sub2">
			<div class="grouper">---- FP ----</div>

		</div>
		<div id="sub3">



			<ul class="tabs">
				<li class="tab-link current" data-tab="tab-1">Profile</li>
				<li class="tab-link" data-tab="tab-2">Status</li>
			</ul>
			<div id="tab-1" class="tab-content_profile current ">
				<div class="column">
					<img src="images/profile.png">
					<div class="fpl-pop">FPL</div>
					<a href="#popup1">Edit Profile</a>
				</div>
			</div>
			<div id="tab-2" class="tab-content_profile">
				<div class="status_bar">
					<input type="text" placeholder="Your Status" maxlength="20" ng-model="mood_msg" ng-blur="uCommunication.moodmsg_func(mood_msg)" />
				</div>
				<div class="predefined_stat">
					<ul>
						<li ng-click="uCommunication.moodmsg_func('Online')"><div class="online_stat c_online"></div>Online</li>
						<li ng-click="uCommunication.moodmsg_func('Busy')"><div class="online_stat c_busy"></div>Busy</li>
						<li ng-click="uCommunication.moodmsg_func('Offline')"><div class="online_stat c_offline"></div>Offline</li>
						<li ng-click="uCommunication.moodmsg_func('Away')"><div class="online_stat c_away"></div>Away</li>
						<li ng-click="uCommunication.moodmsg_func('Do Not Disturb')"><div class="online_stat c_dontdisturb"></div>Do not Disturb</li>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<!-- tooltip code end -->
	<!-- fil popup start -->
	<div id="popup1" class="overlay">
		<div class="popup">
			<a class="close" href="#">&times;</a>
			<form enctype="multipart/form-data">
				<div class="form-group">
					<input id="file-1" type="file" multiple class="file"
						data-overwrite-initial="false" data-min-file-count="1">
				</div>
			</form>
		</div>
	</div>




</div>
	<!--EO chatbox -->
	<!-- tooltip start -->

	<!-- tooltp end -->
	<!-- file input scrtipt start -->
	<script>
	        
    

</script>
	

	<script>
		$("#file-1").fileinput({
			uploadUrl : '#', // you must set a valid URL here else you will get an error
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
			overwriteInitial : false,
			maxFileSize : 1000,
			maxFilesNum : 10,
			//allowedFileTypes: ['image', 'video', 'flash'],
			slugCallback : function(filename) {
				return filename.replace('(', '_').replace(']', '_');
			}
		});

		$(document).ready(function() {
			$("#test-upload").fileinput({
				'showPreview' : false,
				'allowedFileExtensions' : [ 'jpg', 'png', 'gif' ],
				'elErrorContainer' : '#errorBlock'
			});

		});
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	<!-- file input script end -->
	<script>
		$(window).load(
				function() {
					$("[data-toggle]").click(function() {
						var toggle_el = $(this).data("toggle");
						$(toggle_el).toggleClass("open-sidebar");
					});
					$(".swipe-area")
							.swipe(
									{
										swipeStatus : function(event, phase,
												direction, distance, duration,
												fingers) {
											if (phase == "move"
													&& direction == "right") {
												$(".mobile-devi").addClass(
														"open-sidebar");
												return false;
											}
											if (phase == "move"
													&& direction == "left") {
												$(".mobile-devi").removeClass(
														"open-sidebar");
												return false;
											}
										}
									});
				});
	</script>
	<script>
		$(document).ready(function() {
			$(".drop-compose").click(function() {
				$(".drob_compose-item").toggle(500);
			});
		});
	</script>
	<!-- mobile end -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

	<!-- Include all compiled plugins (below), or include individual files as needed -->

	<script type="text/javascript">
		$('.hastip').tooltipsy({
			content : '<ul><a href="#" ><li>Report Abuse </li></a></ul>',
			offset : [ 0, 1 ]
		});
		/* $(document).ready(function() {

			$("body").on('click', 'li.chat_main_list', function(event) {
				var contentPanelId = $(this).attr("id");
				contentPanelId = contentPanelId.replace(/\D/g, '');
				var appendText = '#chat_contain';
				contentPanelId = appendText + '1';
				$(contentPanelId).show();
			});
		}); */

		/* $(function() {
			$('.chat_contain').attr('style', 'bottom:21.45em;');
			$('.chat_contain').hide();
			$('.chat_contain').addClass('opened');

		}); */
		// for the profile pic tab
		$(document).ready(function() {

			$('ul.tabs li').click(function() {
				var tab_id = $(this).attr('data-tab');

				$('ul.tabs li').removeClass('current');
				$('.tab-content_profile').removeClass('current');

				$(this).addClass('current');
				$("#" + tab_id).addClass('current');
			})

		});

		// for the profile pic tab

		//tooltip settings

		//tooltip settings
	</script>
</div>





