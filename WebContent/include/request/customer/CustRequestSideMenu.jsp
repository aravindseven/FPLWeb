<script language="JavaScript">
     	function submitMe(form,actionURL){
           	form.action = actionURL;
            form.submit();
     	}
</script>

<div class="col-lg-2 col-md-2 col-sm-2">
	<form name="RequestMenuForm" method="post">
		<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary">
	    	<ul class="nav bs-docs-sidenav">
	        	<li>
	        		<a href="initRequest.do">Request List</a>
	        	</li>
	        	<li>
	        		<a href="#" onclick="submitMe(document.RequestMenuForm,'initNewRequest.do')">New Request</a>
	        	</li>
	        	<li ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
	        		<a href="initRequest.do">Pending Request</a>
	        	</li>          
	        </ul>
	    </div>
    </form>
</div>
