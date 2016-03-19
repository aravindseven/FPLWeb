package com.fpl.core.profile.financialplanner;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.fpl.FPLException;
import com.fpl.common.AbstractTransformer;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.financialplanner.FplDomainConfig;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.profile.personal.PersonalData;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.DateUtil;
import com.fpl.util.FPLBeanUtilsAdapter;

public class FinancialPlannerPVTransformer extends AbstractTransformer<FinancialPlanner, FinancialPlannerPV> {
	
	private IDomainDAO domainDAO;
	
	@Override
	public FinancialPlannerPV transform(final FinancialPlanner planner) {
		final FinancialPlannerPV plannerPV = new FinancialPlannerPV();
		if(planner != null) {
			final PersonalData personalData = planner.getPersonalData();
			plannerPV.setPersonalDataPV(getPersonalDataPV(personalData));
			final PersonalData secondaryData = planner.getSecondaryPersonData();
			if(secondaryData!=null)
			{
				
				plannerPV.setSecondaryDataPV(getPersonalDataPV(secondaryData));
			}

			try {
				if(personalData.getAddress() != null) {
					final AddressPV addressPV = new AddressPV();
					FPLBeanUtilsAdapter.copyProperties(personalData.getAddress(), addressPV);
					plannerPV.setAddressPV(addressPV);
				}
				if(personalData.getPermanentAddress() != null) {
					final AddressPV permanentAddress = new AddressPV();
					FPLBeanUtilsAdapter.copyProperties(personalData.getPermanentAddress(), permanentAddress);
					plannerPV.setPermanentAddressPV(permanentAddress);
				}
			} catch (final IllegalAccessException e) {
				throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM,e.getMessage());
			} catch (final InvocationTargetException e) {
				throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM,e.getMessage());
			}
			
		}
		setFinancialPlannerPV(planner, plannerPV);
		plannerPV.setDomainList(getFplDomainConfig(planner.getFplDomainCfgs()));
		return plannerPV;
	}
	
	private PersonalDataPV getPersonalDataPV(final PersonalData personalData) {
		final PersonalDataPV personalDataPV = new PersonalDataPV();
		personalDataPV.setDob(DateUtil.getFormattedDate(personalData.getDateOfBirth(), "yyyy-MM-dd"));
		personalDataPV.setEmail(personalData.getEmail());
		personalDataPV.setFirstName(personalData.getName());
		personalDataPV.setLastName(personalData.getLastNameOrRNumber());
		personalDataPV.setGender(personalData.getGenderOrLoc());
		personalDataPV.setIdProof(personalData.getProofDesc());
		personalDataPV.setLandLine(personalData.getLandlineNum());
		personalDataPV.setMobile(personalData.getMobileNumber());
		personalDataPV.setProofNum(personalData.getProofNum());
		return personalDataPV;
	}

	private void setFinancialPlannerPV(final FinancialPlanner planner, final FinancialPlannerPV plannerPV) {
		plannerPV.setFplId(planner.getId().toString());
		plannerPV.setRegistration(planner.getRegistration());
		plannerPV.setAgencyName(planner.getAgencyName());
		plannerPV.setCoverage(planner.getCoverage());
		plannerPV.setInsuranceCompany(planner.getInsuranceCompany());
		final String validationDate = DateUtil.getFormattedDate(planner.getUpdationDate());
		plannerPV.setValidationDate(validationDate);
		plannerPV.setStatus(ProfileStatus.valueOf(planner.getStatus().toUpperCase()).getStatus());
		plannerPV.setState(ProfileState.valueOf(planner.getState().toUpperCase()).getValue());
		plannerPV.setValidationDate(DateUtil.getFormattedDate(planner.getUpdationDate()));
		final String regEntity = DateUtil.getFormattedDate(planner.getRegEntity(), "yyyy-MM-dd");
		plannerPV.setRegEntity(regEntity);
		final String expiryDate = DateUtil.getFormattedDate(planner.getExpiryDate(), "yyyy-MM-dd");
		plannerPV.setExpiryDate(expiryDate);
		plannerPV.setSpecialization1(planner.getSpecialization1());
		plannerPV.setSpecialization2(planner.getSpecialization2());
		plannerPV.setWebsite(planner.getWebsite());
		plannerPV.setDomains(domainDAO.getAllEntities());
		plannerPV.setCreationDate(planner.getDate());
	}
	
	private List<String> getFplDomainConfig(final Set<FplDomainConfig> domainConfigs) {
		final Collection<String> domains = new AbstractTransformer<FplDomainConfig, String>() {
			@Override
			public String transform(final FplDomainConfig domainConfig) {
				return domainConfig.getDomainInfo().getName();
			}
		}.transform(domainConfigs);
		return (List<String>) domains;
	}
	
	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
}
