
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form action="" name="ChatSearchRequestForm" id="ChatSearchRequestFormId" class="col-sm-12" method="post">
<div class="form-horizontal">
	 <div class="form-group">
	 <table class="table table-striped" style="margin-bottom: 0px" id="searchMailTable">
    	<tbody>
        	<tr>
	  			 <td>
	  			 	<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<input type="text" class="form-control input-medium search-query" title="search" id="search" name="search" placeholder="Name"/>
	  			 	</div>
	    		</td>	 		    		
	    		<td>
	    			<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<button type="submit" id="searchMail" class="btn btn-primary">Search</button>
	    			</div>
	    		</td>	
	    	</tr>
	    </tbody>
	   </table>	   
	 </div>	
</div>	    	
</form>