<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MY PROFILE START -->
<div class="innerWrapper"   style="min-height:150px">
	<div class="row innerContentWrapper mySettingsWrapper">
		<div class="tabbable">
			<div class="titleHeaderBlue">Subscription</div>
			<div class="tab-content clear paddingLnone">
				<div id="subscription" class="tab-pane fade active in paddingLnone profileTabContent">
					<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
						<jsp:include page="/include/admin/SubscriptionSideMenu.jsp" flush="true" />
						<% 
						String varType = request.getParameter("type");
						if(varType != null && varType.equals("listAdv")){%>
						<jsp:include page="/include/admin/listAdvTypes.jsp" flush="true" />
						<%}
						else if(varType != null && varType.equals("createAdv")){%>
							<jsp:include page="/include/admin/advTypes.jsp" flush="true" />
							
						<%} else if(varType != null && varType.equals("advRegistration")){%>
							<jsp:include page="/include/admin/advRegistration.jsp" flush="true" /> 
							
						<%} else if(varType != null && varType.equals("listSub")){%>
						<jsp:include page="/include/admin/listAdvSubscription.jsp" flush="true" />
						
						<%} else if(varType != null && varType.equals("createSub")){%>
							<jsp:include page="/include/admin/advSubscription.jsp" flush="true" />
							
						<%} else if(varType != null && varType.equals("listFPType")){%>
						<jsp:include page="/include/admin/listFPType.jsp" flush="true" />
						
						<%} else if(varType != null && varType.equals("createFPSub")){%>
							<jsp:include page="/include/admin/FPSubscription.jsp" flush="true" />
							
						<%} else if(varType != null && varType.equals("listFPSub")){%>
						<jsp:include page="/include/admin/listFPSubscription.jsp" flush="true" />
							
						<%} else if(varType != null && varType.equals("createFPType")){%>
							<jsp:include page="/include/admin/FpSubscriptionTypes.jsp" flush="true" />
						<%} else {%>
							<jsp:include page="/include/admin/listFPType.jsp" flush="true" />
						<%} %>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MY PROFILE END -->