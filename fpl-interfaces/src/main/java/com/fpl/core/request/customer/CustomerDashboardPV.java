package com.fpl.core.request.customer;

import java.util.ArrayList;
import java.util.List;

import com.fpl.core.request.RequestActivityDTO;

public class CustomerDashboardPV extends NewRequestPV {

	private String status;
	List<RequestActivityDTO> requestFPLList;
	List<RequestActivityDTO> secondRequestFPLList;

	public List<RequestActivityDTO> getRequestFPLList() {
		
		if(requestFPLList==null)
		{
			requestFPLList=new ArrayList<RequestActivityDTO>();
		}
		return requestFPLList;
	}

	public void setRequestFPLList(List<RequestActivityDTO> requestFPLList) {
		this.requestFPLList = requestFPLList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RequestActivityDTO> getSecondRequestFPLList() {
		return secondRequestFPLList;
	}

	public void setSecondRequestFPLList(
			List<RequestActivityDTO> secondRequestFPLList) {
		this.secondRequestFPLList = secondRequestFPLList;
	}
	
	
	
	
}
