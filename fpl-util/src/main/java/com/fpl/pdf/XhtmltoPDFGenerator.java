package com.fpl.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.WordUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.fpldb.Fpldb;
import com.fpl.util.DateUtil;
import com.fpl.utils.ConvertNumberUtils;
import com.lowagie.text.DocumentException;

public class XhtmltoPDFGenerator {


	
	public  OutputStream generatePDF(FPSubscriptionListPV fpSubscriptionListPV,Fpldb fpldb,String type)
	{
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		StringBuffer htmlBuffer=new StringBuffer();
		if("PRINT_INVOICE".equalsIgnoreCase(type))	
		{
			htmlBuffer=buildInvoiceHtml(fpSubscriptionListPV,fpldb);
		}else
		{
			htmlBuffer=buildHtml(fpSubscriptionListPV,fpldb);
		}
		
		try {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlBuffer.toString());
			renderer.layout();
			renderer.createPDF( outputStream );
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return outputStream;
	}
	
	
	
	private StringBuffer buildHtml(FPSubscriptionListPV fpSubscriptionListPV,Fpldb fpldb)
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"     \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		buffer.append( "<html xmlns=\"http://www.w3.org/1999/xhtml\">");

		buffer.append( "<head><title>Title of document</title></head>");

		
		buffer.append( "<body> ");
		buffer.append( "<table border='0' width='100%' style='border:solid 2px;' cellspacing='0' cellpadding='0'>");
		buffer.append( "<tr><td align='center' colspan='3'>&nbsp;</td></tr> ");
		buffer.append( "<tr><td align='center' colspan='3'>   <b style='font-size: 24px;'>  PRO FORMA INVOICE  </b>  </td></tr> ");
		buffer.append( "<tr><td align='center' colspan='3'>&nbsp;</td></tr> ");
		
		buffer.append( "<tr><td width='20%'>");
		buffer.append( "<img src='http://localhost:8080/FPLWeb/support/images/1.png' width='168px' height='118px'/>");
		
		buffer.append( "</td> <td width='40%'>&nbsp;</td>");
		buffer.append( "<td width='40%' align='left'>" +
				"<table border='0' width='100%'> " +
				" <tr>    <td align='left'>  <b style='font-size: 18px;'>" +
				fpldb.getName() +
				"</b> </td> <td>&nbsp;</td>  </tr>");
		buffer.append( "   <tr>     <td align='left'>    UEN / GST Reg No. " +
				fpldb.getRegistrationId() +
				"</td> <td>&nbsp;</td> </tr>");
		buffer.append( "  <tr>    <td align='left'>");
		buffer.append( "     <b> Registered  Address :</b> <br/>    " +
				fpldb.getBlock() +
				","+
				fpldb.getStreet()+
				", <br/> " +
				fpldb.getBuildingName()+
				", <br/>");
		buffer.append( fpldb.getCountry()+
				"&nbsp;"+
				fpldb.getPostalCode()+
				"<br/>    TEL: 65-" +
				fpldb.getMobile()+
				"<br/>    FAX: 65-  " +
				fpldb.getLandline()+
				"  </td> <td>&nbsp;</td> </tr>");
		buffer.append( "  <tr>  <td>    &nbsp;  </td>  </tr>");
		
				buffer.append(		" </table>");
		buffer.append( "</td>");
		buffer.append( "</tr>");
		buffer.append( "<tr><td colspan='3' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");

		buffer.append( "<tr><td colspan='3'>");
		
		
		buffer.append( "<table border='0' width='100%'>");
        buffer.append( "<tr> <td colspan='2'>");
        buffer.append( "<table border='0' width='100%'>");
        buffer.append( "<tr>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "<td>Name</td>");
        buffer.append( "<td>" +fpSubscriptionListPV.getContactName()+
        		"</td>");
        buffer.append( "<td>Subscription date</td>");
        buffer.append( "<td>" +DateUtil.getFormattedDate(fpSubscriptionListPV.getValidationDate())+
        		"</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nsbp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "<td>Subscription ID </td>");
        buffer.append( "<td>" +
        		fpSubscriptionListPV.getIdText()+
        		"</td>");
        buffer.append( "<td>&nsbp;</td>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nsbp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "<td>Payment Mode  </td>");
        buffer.append( "<td>Cheque </td>");
        buffer.append( "<td>Cheque date </td>");
        buffer.append( "<td>___________</td>");
        buffer.append( "</tr> </table>");
        buffer.append( "</td> </tr> ");
        
        buffer.append( "<tr><td colspan='3' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='3'>");
        buffer.append( "<table border='0' width='100%' cellspacing='0' cellpadding='0'>");
        buffer.append( "<tr>");
        buffer.append( "<td>Subscription Plan</td>");
        buffer.append( "<td>Duration</td>");
        buffer.append( "<td align='center'>Rate</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td>Amount</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr><td colspan='5' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");
        
        
        buffer.append( "<tr><td colspan='5'>&nbsp;</td></tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td>"+
        		fpSubscriptionListPV.getSubscriptionMasterPV().getType() +
        		"</td>");
        buffer.append( "<td>" +
        		fpSubscriptionListPV.getSubscriptionMasterPV().getPlanFrequency() +
        		" Year/s</td>");
        buffer.append( "<td align='center'> S$" +
        		fpSubscriptionListPV.getSubscriptionAmount() +
        		"</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td align='left'> S$" +
        		fpSubscriptionListPV.getSubscriptionAmount()+
        		"</td>");
        buffer.append( "</tr>");
        
        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");

        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td>GST " +fpSubscriptionListPV.getSubscriptionMasterPV().getCountryPV().getSaleTaxRate().intValue()+
        		"%</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td align='left'>S$" +
        		fpSubscriptionListPV.getSubscriptionTaxAmount() +
        		"</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td > &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");

        
        buffer.append( "<tr>");
        buffer.append( "<td > &nbsp;</td>");
        buffer.append( "<td > &nbsp;</td>");
        buffer.append( "<td >Total Amount:</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td align='left' >S$" +
        		fpSubscriptionListPV.getSubscriptionTotalAmount() +
        		"</td>");
        buffer.append( "</tr>");

        
        buffer.append( "</table>");
        buffer.append( "</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr><td colspan='4' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nbsp; </td>");
        buffer.append( "</tr>");
    
        buffer.append( "<tr>");
        
        buffer.append( "<td colspan='4'>Amount in words: <span style='text-decoration: underline;'> Singapore Dollar " +
        WordUtils.capitalize(new ConvertNumberUtils().convert(fpSubscriptionListPV.getSubscriptionTotalAmount().intValue()))+
        		" Only &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>");
        
        
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nbsp; </td>");
        buffer.append( "</tr>");
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>Instructions </td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        
        buffer.append( "<td colspan='4'>1. Print  a copy of this proforma invoice and mail it to our address with your cheque for the above mentioned amount. </td>");
        buffer.append( "</tr>");
        buffer.append( "<tr>");
        
        buffer.append( "<td colspan='4'>2. Subscription id is your reference number ,in case you need to check with us for any queries .  </td>");
        buffer.append( "</tr>");
        

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        
        buffer.append( "<td colspan='4'> THIS IS A COMPUTER GENERATED DOCUMENT AND NO SIGNATURE IS REQUIRED</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "</table>");
        buffer.append( "</td></tr></table>");
        //With border table
//        buffer.append( "</td></tr></table>");
		buffer.append( "</body></html>" );
		System.out.println(buffer);
		return buffer;
	}
	

	private StringBuffer buildInvoiceHtml(FPSubscriptionListPV fpSubscriptionListPV,Fpldb fpldb)
	{
		StringBuffer buffer=new StringBuffer();
		buffer.append( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"     \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		buffer.append( "<html xmlns=\"http://www.w3.org/1999/xhtml\">");

		buffer.append( "<head><title>Title of document</title></head>");

		
		buffer.append( "<body> ");
		buffer.append( "<table border='0' width='100%' style='border:solid 2px;' cellspacing='0' cellpadding='0'>");
		buffer.append( "<tr><td align='center' colspan='3'>&nbsp;</td></tr> ");
		buffer.append( "<tr><td align='center' colspan='3'>   <b style='font-size: 24px;'>  TAX INVOICE  </b>  </td></tr> ");
		buffer.append( "<tr><td align='center' colspan='3'>&nbsp;</td></tr> ");
		
		buffer.append( "<tr><td width='20%'>");
		buffer.append( "<img src='http://localhost:8080/FPLWeb/support/images/1.png' width='168px' height='118px'/>");
		
		buffer.append( "</td> <td width='40%'>&nbsp;</td>");
		buffer.append( "<td width='40%' align='left'>" +
				"<table border='0' width='100%'> " +
				" <tr>    <td align='left'>  <b style='font-size: 18px;'>" +
				fpldb.getName() +
				"</b> </td> <td>&nbsp;</td>  </tr>");
		buffer.append( "   <tr>     <td align='left'>    UEN / GST Reg No. " +
				fpldb.getRegistrationId() +
				"</td> <td>&nbsp;</td> </tr>");
		buffer.append( "  <tr>    <td align='left'>");
		buffer.append( "     <b> Registered  Address :</b> <br/>    " +
				fpldb.getBlock() +
				","+
				fpldb.getStreet()+
				", <br/> " +
				fpldb.getBuildingName()+
				", <br/>");
		buffer.append( fpldb.getCountry()+
				"&nbsp;"+
				fpldb.getPostalCode()+
				"<br/>    TEL: 65-" +
				fpldb.getMobile()+
				"<br/>    FAX: 65-  " +
				fpldb.getLandline()+
				"  </td> <td>&nbsp;</td> </tr>");
		buffer.append( "  <tr>  <td>    &nbsp;  </td>  </tr>");
		
				buffer.append(		" </table>");
		buffer.append( "</td>");
		buffer.append( "</tr>");
		buffer.append( "<tr><td colspan='3' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");

		buffer.append( "<tr><td colspan='3'>");
		
		
		buffer.append( "<table border='0' width='100%'>");
        buffer.append( "<tr> <td colspan='2'>");
        buffer.append( "<table border='0' width='100%'>");
        buffer.append( "<tr>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "<td>Name</td>");
        buffer.append( "<td>" +fpSubscriptionListPV.getContactName()+
        		"</td>");
        buffer.append( "<td>Subscription date</td>");
        buffer.append( "<td>" +DateUtil.getFormattedDate(fpSubscriptionListPV.getValidationDate())+
        		"</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nsbp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "<td>Subscription ID </td>");
        buffer.append( "<td>" +
        		fpSubscriptionListPV.getIdText()+
        		"</td>");
        buffer.append( "<td>Invoice #</td>");
        buffer.append( "<td>" +
        		fpSubscriptionListPV.getInvoiceNumber()+
        		"</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nsbp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td>&nbsp;</td>");
        buffer.append( "<td>Payment Mode  </td>");
        buffer.append( "<td> " +
        		fpSubscriptionListPV.getMode() +
        		"</td>");
        buffer.append( "<td>Payment date </td>");
        buffer.append( "<td>"+
        		fpSubscriptionListPV.getValidationDate()+
        		"</td>");
        buffer.append( "</tr> </table>");
        buffer.append( "</td> </tr> ");
        
        buffer.append( "<tr><td colspan='3' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='3'>");
        buffer.append( "<table border='0' width='100%' cellspacing='0' cellpadding='0'>");
        buffer.append( "<tr>");
        buffer.append( "<td>Subscription Plan</td>");
        buffer.append( "<td>Duration</td>");
        buffer.append( "<td align='center'>Rate</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td>Amount</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr><td colspan='5' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");
        
        
        buffer.append( "<tr><td colspan='5'>&nbsp;</td></tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td>"+
        		fpSubscriptionListPV.getSubscriptionMasterPV().getType() +
        		"</td>");
        buffer.append( "<td>" +
        		fpSubscriptionListPV.getSubscriptionMasterPV().getPlanFrequency() +
        		" Year/s</td>");
        buffer.append( "<td align='center'> S$" +
        		fpSubscriptionListPV.getSubscriptionAmount() +
        		"</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td align='left'> S$" +
        		fpSubscriptionListPV.getSubscriptionAmount()+
        		"</td>");
        buffer.append( "</tr>");
        
        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");

        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td>GST " +fpSubscriptionListPV.getSubscriptionMasterPV().getCountryPV().getSaleTaxRate().intValue()+
        		"%</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td align='left'>S$" +
        		fpSubscriptionListPV.getSubscriptionTaxAmount() +
        		"</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td > &nbsp;</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "</tr>");

        
        buffer.append( "<tr>");
        buffer.append( "<td > &nbsp;</td>");
        buffer.append( "<td > &nbsp;</td>");
        buffer.append( "<td >Total Amount:</td>");
        buffer.append( "<td> &nbsp;</td>");
        buffer.append( "<td align='left' >S$" +
        		fpSubscriptionListPV.getSubscriptionTotalAmount() +
        		"</td>");
        buffer.append( "</tr>");

        
        buffer.append( "</table>");
        buffer.append( "</td>");
        buffer.append( "</tr>");
        
        buffer.append( "<tr><td colspan='4' style='border-style: solid; border-right:none; border-top:none; border-left:none;'>&nbsp;</td></tr>");
        
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nbsp; </td>");
        buffer.append( "</tr>");
    
        buffer.append( "<tr>");
        
        buffer.append( "<td colspan='4'>Amount in words: <span style='text-decoration: underline;'> Singapore Dollar " +
        WordUtils.capitalize(new ConvertNumberUtils().convert(fpSubscriptionListPV.getSubscriptionTotalAmount().intValue()))+
        		" Only &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>");
        
        
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>&nbsp; </td>");
        buffer.append( "</tr>");
        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'>Note: </td>");
        buffer.append( "</tr>");
        buffer.append( "<tr>");
        
        buffer.append( "<td colspan='4'> THIS IS A COMPUTER GENERATED DOCUMENT AND NO SIGNATURE IS REQUIRED</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "<tr>");
        buffer.append( "<td colspan='4'> &nbsp;</td>");
        buffer.append( "</tr>");

        buffer.append( "</table>");
        buffer.append( "</td></tr></table>");
        //With border table
//        buffer.append( "</td></tr></table>");
		buffer.append( "</body></html>" );
		System.out.println(buffer);
		return buffer;
	}

}
