package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.core.profile.company.ICompanyPersister;
import com.fpl.profile.company.CompanyPV;

public class CompanyRegisterService implements ICompanyRegisterService {
	
	private ICompanyPersister persister;
	
	@Override
	public FPLServiceResponse insertCompany(final CompanyPV companyPV) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			persister.persistCompany(companyPV);
			response.setReason("Company Profile Created !");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}

	public void setPersister(final ICompanyPersister persister) {
		this.persister = persister;
	}
}
