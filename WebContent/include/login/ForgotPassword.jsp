<!-- FORGOT PASSWORD START -->
<div class="innerWrapper">
	<div class="innerContentWrapper">    
   		<div class="titleHeaderBlue">Change Password</div> <br />
   		
		<jsp:include page="/include/common/ErrorDisplay.jsp" flush="true" />
   		
   		<form name="passwordResetForm" method="post" action="fplPasswordReset.do">
     		<div class="form-group marginTfifteen">
       			Email id: ${passwordRestParam.email}
     		</div> <br />
     		<div class="form-group marginTfifteen">
       			<input type="password" name="newPassword" class="form-control" placeholder="New Password"/>
     		</div> <br />
     		<div class="form-group marginTfifteen">
       			<input type="password" name="conformPassword" class="form-control" placeholder="Confirm Password"/>
     		</div> <br />
     		<input type="hidden" name="otp" value="${passwordRestParam.otp}">
     		<input type="hidden" name="email" value="${passwordRestParam.email}">
    		<button type="submit" class="btn btnPrimaryBlue">Change</button>
   		</form>
  	</div>
</div>
<!-- FORGOT PASSWORD END -->
