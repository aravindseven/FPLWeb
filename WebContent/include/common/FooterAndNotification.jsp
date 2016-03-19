<!-- Footer Start -->
<div class="footer errorFooter">
	<ul class="pull-left">
		<li><a href="#">About Us</a>
		</li>
		<li><a href="#">Privacy Policy</a>
		</li>
		<li><a href="#">Terms and Conditions</a>
		</li>
		<li><a href="#">Copyright</a>
		</li>
	</ul>
</div> 
<!-- Footer End -->

<!--Notification Start-->
<div id="notify-success"></div>
<div id="notify-failure"></div>
<div id="notify-info"></div> 
<!--Notification End-->

<div ng-controller="CommunicationController as uchat">
	<jsp:include page="/include/communication/globalChat.jsp" flush="true"/>
</div>

