package com.fpl.core.profile.admin;

import java.util.ArrayList;
import java.util.Collection;

import com.fpl.common.AbstractTransformer;
import com.fpl.company.Company;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.company.ICompanyDAO;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.admin.ProfileSearchResultPV;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.profile.customer.Customer;
import com.fpl.profile.personal.PersonalData;
import com.fpl.util.DateUtil;

public class ProfileSearcher implements IProfileSearcher {
	
	private ICustomerDAO customerDAO;
	private IFinancialPlannerDAO fplDAO;
	private ICompanyDAO companyDAO;
	
	@Override
	public Collection<ProfileSearchResultPV> getProfileList(final SearchProfilePV searchProfile) {
		final Collection<ProfileSearchResultPV> searchResultList = new ArrayList<ProfileSearchResultPV>();
		final Collection<Customer> customers = customerDAO.getCustomerList(searchProfile);
		final Collection<FinancialPlanner> financialPlanners = fplDAO.getFinancialPlannerList(searchProfile);
		final Collection<Company> companies = companyDAO.getCompanyList(searchProfile);
		searchResultList.addAll(getCustomerTransformer().transform(customers));
		searchResultList.addAll(getFplTransformer().transform(financialPlanners));
		searchResultList.addAll(getCompanyTransformer().transform(companies));
		return searchResultList;
	}
	
	private AbstractTransformer<Customer, ProfileSearchResultPV> getCustomerTransformer() {
		return new AbstractTransformer<Customer, ProfileSearchResultPV>() {
			@Override
			public ProfileSearchResultPV transform(final Customer customer) {
				final ProfileSearchResultPV searchResult = new ProfileSearchResultPV();
				final PersonalData personalData =  customer.getPersonalData();
				searchResult.setUserType(""+customer.getUserTypeId());
				searchResult.setDob(DateUtil.getFormattedDate(personalData.getDateOfBirth()));
				searchResult.setEmail(personalData.getEmail());
				searchResult.setMobileNumber(personalData.getMobileNumber());
				searchResult.setName(personalData.getName());
				searchResult.setId(""+customer.getId());
				return searchResult;
			}
		};
	}
	
	private AbstractTransformer<FinancialPlanner, ProfileSearchResultPV> getFplTransformer() {
		return new AbstractTransformer<FinancialPlanner, ProfileSearchResultPV>() {
			@Override
			public ProfileSearchResultPV transform(final FinancialPlanner planner) {
				final ProfileSearchResultPV searchResult = new ProfileSearchResultPV();
				final PersonalData personalData =  planner.getPersonalData();
				searchResult.setUserType(""+planner.getUserTypeId());
				searchResult.setDob(DateUtil.getFormattedDate(personalData.getDateOfBirth()));
				searchResult.setEmail(personalData.getEmail());
				searchResult.setMobileNumber(personalData.getMobileNumber());
				searchResult.setName(personalData.getName());
				searchResult.setId(""+planner.getId());
				return searchResult;
			}
		};
	}
	
	private AbstractTransformer<Company, ProfileSearchResultPV> getCompanyTransformer() {
		return new AbstractTransformer<Company, ProfileSearchResultPV>() {
			@Override
			public ProfileSearchResultPV transform(final Company company) {
				final ProfileSearchResultPV searchResult = new ProfileSearchResultPV();
				searchResult.setUserType("Company");
				searchResult.setDob(null);
				searchResult.setEmail(company.getEmail());
				//searchResult.setMobileNumber(company.getLandLine());
				searchResult.setName(company.getName());
				searchResult.setId(""+company.getId());
				return searchResult;
			}
		};
	}
	
	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	/**
	 * @param fplDAO the fplDAO to set
	 */
	public void setFplDAO(final IFinancialPlannerDAO fplDAO) {
		this.fplDAO = fplDAO;
	}

	/**
	 * @param companyDAO the companyDAO to set
	 */
	public void setCompanyDAO(final ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
}
