<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!-- MY PROFILE START -->
 <div class="innerWrapper">
 	<div class="row innerContentWrapper myProfileWrapper">
    	<div class="tabbable">
        <div class="titleHeaderBlue"> Companies Registration </div>
        <div class="tab-content clear paddingLnone">
        	<div id="profile" class="tab-pane fade active in paddingLnone profileTabContent">
            <div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
            	<div class="col-lg-12 col-md-12 col-sm-12 profileTabForm">
            		<div class="row">
            			<form action="fplCompanyCreation.do" method="post" class="col-sm-12">
							<jsp:include page="/include/profile/CompanyInformation.jsp" flush="true" />
            				<jsp:include page="/include/profile/CompanyContactInformation.jsp" flush="true" />
                		</form>
                  	</div>
                </div>
            </div>
            </div>
        </div>
        </div>
    </div>
</div>
<!-- MY PROFILE END -->

