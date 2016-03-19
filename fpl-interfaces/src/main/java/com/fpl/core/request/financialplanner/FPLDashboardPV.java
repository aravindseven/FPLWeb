package com.fpl.core.request.financialplanner;

import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.customer.NewRequestPV;

public class FPLDashboardPV extends NewRequestPV {

	private String status;
	private String customerId;
	private RequestActivityDTO  requestActivityDTO;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public RequestActivityDTO getRequestActivityDTO() {
		return requestActivityDTO;
	}
	public void setRequestActivityDTO(RequestActivityDTO requestActivityDTO) {
		this.requestActivityDTO = requestActivityDTO;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	
	

}
