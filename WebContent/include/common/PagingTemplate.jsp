<%@page import="com.fpl.paging.IPagingData"%>
<%
	final IPagingData hbPage = (IPagingData) request.getAttribute("PagingData");
	if ( hbPage != null && hbPage.getTotalPages() > 1 )	{
%>

<table width="100%">
	<tr>
		<td align="center">
		<Font style="font-family: Verdana;font-size: 11px;">
<%		if ( hbPage.hasPrevious() ) {  %>
				<a name="fplPagingFirstBlock"
				   href="#" 
				   onMouseOver="link('First Block');return true;" 
				   onMouseOut="cancella()"
				   onClick="submitPageNo(<%=hbPage.getFirstBlockFirstPage()%>)"><b>&lt;&lt;</b>
				</a>&nbsp;&nbsp;&nbsp;
				<a name="fplPagingPreviousBlock" 
				   href="#" 
				   onMouseOver="link('Previous Block');return true;"
				   onMouseOut="cancella()"
				   onClick="submitPageNo(<%=hbPage.getPrevBlockFirstPage()%>)"><b>&lt;</b>
				</a>&nbsp;&nbsp;&nbsp;
<%  	}
		for ( int i = hbPage.getStartingPageNo() ; i <= hbPage.getEndingPageNo() ; i++ ) {
	   		if ( i == hbPage.getCurrentPageNo()) {
%>
        	<Font color=red><b><%=i %></b></Font>&nbsp;&nbsp;&nbsp;
<%      	} else {
%>
				<a name="fplPageNumberBlock"
				   href="#" 
				   onMouseOver="link('Page No <%=i%>');return true;"
				   onMouseOut="cancella()"
				   onClick="submitPageNo(<%=i %>)"><b><%=i%></b>
				</a> &nbsp;&nbsp;&nbsp;
<%
			}
		}
    	if ( hbPage.hasSuccessive() ) {
%>&nbsp;&nbsp;&nbsp;
 				<a name="fplPageNextBlock" 
				   href="#" 
				   onMouseOver="link('Next Block');return true;"
				   onMouseOut="cancella()"
				   onClick="submitPageNo(<%=hbPage.getNextBlockFirstPage()%>)"><b>&gt;</b>
				</a> &nbsp;&nbsp;&nbsp;
 				<a name="fplPageLastBlock" 
				   href="#" 
				   onMouseOver="link('Last Block');return true;"
				   onMouseOut="cancella()"
				   onClick="submitPageNo(<%=hbPage.getLastBlockFirstPage()%>)"><b>&gt;&gt;</b>
				</a> &nbsp;&nbsp;&nbsp;
<%  	}
%>
</Font>
		</td>
	<tr>
</table>

<%	}
%>
