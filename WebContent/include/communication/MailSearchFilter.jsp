<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form ng-submit="uCommunication.searchMail(message)" autocomplete="off" name="MailSearchRequestForm" id="MailSearchRequestFormId" class="col-sm-12" method="post">
<div class="form-horizontal">
	 <div style="margin-bottom:0px" class="form-group">
	 <table style="margin-bottom:0px" class="table table-striped" id="searchMailTable">
    	<tbody>
        	<tr>
	  			 <td>
	  			 	<div class="col-lg-10 col-md-10 col-sm-10 paddingNone">
	    				<input type="text" class="form-control input-medium search-query" title="search" ng-model="message.search" id="search" name="search" placeholder="From, To, Subject"/>
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