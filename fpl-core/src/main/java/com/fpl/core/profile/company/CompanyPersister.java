package com.fpl.core.profile.company;

import java.util.Date;

import com.fpl.common.AbstractTransformer;
import com.fpl.common.IValidator;
import com.fpl.company.Company;
import com.fpl.persistence.company.ICompanyDAO;
import com.fpl.profile.company.CompanyPV;

public class CompanyPersister implements ICompanyPersister {
	
	private ICompanyDAO companyDAO;
	private IValidator<CompanyPV> companyValidator;
	private AbstractTransformer<CompanyPV, Company> companyTransformer;
	
	@Override
	public void persistCompany(final CompanyPV companyPV) {
		companyValidator.validate(companyPV);
    	final Company company = companyTransformer.transform(companyPV);
    	final Company oldCompany = companyDAO.getCompanyByNameLocation(company.getName(), company.getIncLocation());
    	if(oldCompany != null) {
    		company.setId(oldCompany.getId());
    		company.getAddress().setId(oldCompany.getAddress().getId());
    		company.setUpdationDate(new Date());
    	}
    	companyDAO.saveOrUpdate(company, true);
	}

	/**
	 * @param companyDAO the companyDAO to set
	 */
	public void setCompanyDAO(final ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	/**
	 * @param companyValidator the companyValidator to set
	 */
	public void setCompanyValidator(final IValidator<CompanyPV> companyValidator) {
		this.companyValidator = companyValidator;
	}

	/**
	 * @param companyTransformer the companyTransformer to set
	 */
	public void setCompanyTransformer(final AbstractTransformer<CompanyPV, Company> companyTransformer) {
		this.companyTransformer = companyTransformer;
	}
}


