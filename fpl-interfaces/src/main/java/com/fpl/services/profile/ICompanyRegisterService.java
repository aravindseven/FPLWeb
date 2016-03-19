package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.profile.company.CompanyPV;

public interface ICompanyRegisterService {

	FPLServiceResponse insertCompany(CompanyPV companyPV);
}
