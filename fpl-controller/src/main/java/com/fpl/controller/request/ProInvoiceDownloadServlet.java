package com.fpl.controller.request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.pdf.XhtmltoPDFGenerator;
import com.fpl.util.StringUtil;

public class ProInvoiceDownloadServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		try {
			IFplSubscriptionManager fplSubscriptionManager=(IFplSubscriptionManager)context.getBean("fplSubscriptionManager");
			String subId=req.getParameter("fplSubId");
			String action=req.getParameter("ACTION");
			if(subId!=null)
			{
				FPSubscriptionListPV fpSubscriptionListPV=fplSubscriptionManager.getFplSubscriptionById(Long.parseLong(subId));
				XhtmltoPDFGenerator pdfGenerator=new XhtmltoPDFGenerator();
				if(StringUtil.isNotEmpty(action) && "PRINT_INVOICE".equalsIgnoreCase(action))
				{
					ByteArrayOutputStream outputStream=(ByteArrayOutputStream)pdfGenerator.generatePDF(fpSubscriptionListPV,fplSubscriptionManager.getFPLDB(),action);
					resp.getOutputStream().write(outputStream.toByteArray());
					outputStream.close();
				}else
				{
					ByteArrayOutputStream outputStream=(ByteArrayOutputStream)pdfGenerator.generatePDF(fpSubscriptionListPV,fplSubscriptionManager.getFPLDB(),"PRO_PRINT_INVOICE");
					resp.getOutputStream().write(outputStream.toByteArray());
					outputStream.close();
					
				}
				resp.flushBuffer();
			}
			System.out.println("Test1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Test");
		
		
		
	}

	
}
