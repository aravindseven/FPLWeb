package com.fpl.controller.request;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class UploadServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload();
			// Parse the request
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
			      FileItemStream item = iter.next();
			     String name = item.getFieldName();
			     InputStream stream = item.openStream();
			     if (item.isFormField()) {
			               System.out.println("Form field " + name + " with value " + Streams.asString(stream) + " detected.");
			     } else {
			              System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
			              
			              FileOutputStream fout= new FileOutputStream ("C:/Output Files/img.jpg");
			              int byt;
			              while ((byt=stream.read()) != -1)
			              {
			                  fout.write(byt);
			              }
			              fout.close();
			             
			     }
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
