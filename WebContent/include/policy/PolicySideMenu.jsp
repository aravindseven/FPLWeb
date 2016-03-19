<script language="JavaScript">
     	function submitMe(form,actionURL){
           	form.action = actionURL;
            form.submit();
     	}
</script>

<div class="col-lg-2 col-md-2 col-sm-2">
	<form name="PolicyMenuForm" method="post">
		<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary">
	    	<ul class="nav bs-docs-sidenav">
	        	<li>
	        		<a href="initPolicy.do">Policy List</a>
	        	</li>
	        	<li>
	        		<a href="#" onclick="submitMe(document.PolicyMenuForm,'initNewPolicy.do')">New Policy</a>
	        	</li>	           
	        </ul>
	    </div>
    </form>
</div>
