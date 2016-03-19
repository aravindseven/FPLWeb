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
<div id="notify-success" class="success"></div>
<div id="notify-failure" class="error"></div>
<div id="notify-info" class="info"></div> 
<!--Notification End-->

<!-- Communications -->
		<script type="text/javascript" src="support/controller/CommunicationController.js"></script>
		<script src="https://cdn.socket.io/socket.io-1.2.0.js"></script>
  		<script src="http://requirejs.org/docs/release/2.1.15/minified/require.js"></script>
  		
<div ng-controller="CommunicationController as uchat">
	<jsp:include page="/include/communication/globalChat.jsp" flush="true"/>
</div>

