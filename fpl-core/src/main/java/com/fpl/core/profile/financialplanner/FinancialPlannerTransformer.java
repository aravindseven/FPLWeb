package com.fpl.core.profile.financialplanner;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fpl.FPLException;
import com.fpl.common.AbstractTransformer;
import com.fpl.core.profile.ProfileErrorMessage;
import com.fpl.domain.Domain;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.financialplanner.FplDomainConfig;
import com.fpl.login.entity.LoginCredential;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.persistence.login.ILoginCredentialDAO;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.profile.personal.PersonalData;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

public class FinancialPlannerTransformer extends AbstractTransformer<FinancialPlannerPV, FinancialPlanner> {
	
	private ILoginCredentialDAO credentialDAO;
	private IDomainDAO domainDAO;
	
	@Override
	public FinancialPlanner transform(final FinancialPlannerPV plannerPV) {
		final FinancialPlanner planner = new FinancialPlanner();
		final PersonalData personalData = getPersonData(plannerPV.getPersonalDataPV());
		
		final PersonalData secondaryData = getPersonData(plannerPV.getSecondaryDataPV());
		
		final LoginCredential loginCredential = credentialDAO.getLoginCredentialByMail(personalData.getEmail());
		if(loginCredential == null) {
			throw new FPLException(ProfileErrorMessage.CUSTOMER_NOT_FOUND, personalData.getEmail());
		}else{
			// insertalternativeemailid in credentialtable
						//final String email = Encryption.encrypt(customerParam.getAlternativeemailid(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
			final LoginCredential loginCredential1=credentialDAO.get(loginCredential.getId());
			loginCredential1.setAlternateMailId(plannerPV.getAlternativeemail());
			credentialDAO.update(loginCredential1, true);
		}
		planner.setUserTypeId(loginCredential.getUserTypeId());
		planner.setLoginCredentialId(loginCredential.getId());
		final AddressPV address = plannerPV.getAddressPV();
		if(address != null && isNotEmpty(address)) {
			personalData.setAddress(address);
		}
		final AddressPV permanentAddress = plannerPV.getPermanentAddressPV();
		if(permanentAddress != null && isNotEmpty(permanentAddress)) {
			personalData.setPermanentAddress(permanentAddress);
		}
		planner.setPersonalData(personalData);
		planner.setSecondaryPersonData(secondaryData);
		planner.setFplDomainCfgs(getFplDomainConfig(plannerPV.getDomainList()));
		setFinancialPlannerInfo(planner, plannerPV);
		return planner;
	}
	
	private PersonalData getPersonData(final PersonalDataPV personalDataParam) {
		final PersonalData personalData = new PersonalData();
		
		if(personalDataParam.getDob()!=null)
		{
			final Date dateOfBirth = DateUtil.getFormattedDate(personalDataParam.getDob(), "yyyy-MM-dd");
			personalData.setDateOfBirth(dateOfBirth);
		}	
		
		personalData.setEmail(personalDataParam.getEmail());
		personalData.setGenderOrLoc(personalDataParam.getGender());
		personalData.setLandlineNum(personalDataParam.getLandLine());
		personalData.setLastNameOrRNumber(personalDataParam.getLastName());
		personalData.setMobileNumber(personalDataParam.getMobile());
		personalData.setName(personalDataParam.getFirstName());
		personalData.setProofDesc(personalDataParam.getIdProof());
		personalData.setProofNum(personalDataParam.getProofNum());
		return personalData;
	}

	private Set<FplDomainConfig> getFplDomainConfig(final Collection<String> names) {
		Set<FplDomainConfig> configs = null;
		if(names != null && !names.isEmpty()) {
			configs = new HashSet<FplDomainConfig>();
			final Collection<Domain> domains = domainDAO.getDomainByName(names);
			final Collection<FplDomainConfig>  domainConfigs = new AbstractTransformer<Domain, FplDomainConfig>() {
				@Override
				public FplDomainConfig transform(final Domain domain) {
					final FplDomainConfig domainConfig = new FplDomainConfig();
					domainConfig.setDomainId(domain.getId());
					return domainConfig;
				}
			}.transform(domains);
			configs.addAll(domainConfigs);
		}
		return configs;
	}
	
	private void setFinancialPlannerInfo(final FinancialPlanner planner, final FinancialPlannerPV plannerPV) {
		planner.setRegistration(plannerPV.getRegistration());
		final Date regEntity = DateUtil.getFormattedDate(plannerPV.getRegEntity(), "yyyy-MM-dd");
		final Date expiryDate = DateUtil.getFormattedDate(plannerPV.getExpiryDate(), "yyyy-MM-dd");
		planner.setRegEntity(regEntity);
		planner.setExpiryDate(expiryDate);
		planner.setCoverage(plannerPV.getCoverage());
		planner.setSpecialization1(plannerPV.getSpecialization1());
		planner.setSpecialization2(plannerPV.getSpecialization2());
		planner.setAgencyName(plannerPV.getAgencyName());
		planner.setInsuranceCompany(plannerPV.getInsuranceCompany());
		planner.setWebsite(plannerPV.getWebsite());
		planner.setStatus(plannerPV.getStatus());
		planner.setState(plannerPV.getState());
		planner.setDate(new Date());
	}
	
	private boolean isNotEmpty(final AddressPV address) {
		if(StringUtil.isNotEmpty(address.getBlock())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getBuildingName())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getStreet())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getCity())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getState())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getCountry())) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param credentialDAO the credentialDAO to set
	 */
	public void setCredentialDAO(final ILoginCredentialDAO credentialDAO) {
		this.credentialDAO = credentialDAO;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
}
