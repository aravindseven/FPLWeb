package com.fpl.core.profile.financialplanner;

import java.util.Date;

import com.fpl.common.AbstractTransformer;
import com.fpl.common.IValidator;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.address.IAddressDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.financialplanner.FinancialPlannerPV;

public class FinancialPlannerPersister implements IFinancialPlannerPersister {

    private IFinancialPlannerDAO financialPlannerDAO;
    private IAddressDAO addressDAO;
	private IValidator<FinancialPlannerPV> validator;
	private AbstractTransformer<FinancialPlannerPV, FinancialPlanner> fplTransformer;
	
	@Override
	public void persistCustomer(final FinancialPlannerPV financialPlannerPV) {
		validator.validate(financialPlannerPV);
		final FinancialPlanner financialPlanner = fplTransformer.transform(financialPlannerPV);
		final FinancialPlanner oldFpl = financialPlannerDAO.getFinancialPlannerByLoginId(financialPlanner.getLoginCredentialId());
		final String email = Encryption.encrypt(financialPlannerPV.getAlternativeemail(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
		financialPlanner.getPersonalData().setEmail(email);
		if(oldFpl != null) {
			financialPlanner.setId(oldFpl.getId());
			financialPlanner.getPersonalData().setId(oldFpl.getPersonalData().getId());
			if(oldFpl.getSecondaryPersonData() != null) {
				financialPlanner.getSecondaryPersonData().setId(oldFpl.getSecondaryPersonData().getId());
				financialPlanner.getSecondaryPersonData().setAddress(null);
    		}
			
			if(oldFpl.getPersonalData().getAddress() != null && financialPlanner.getPersonalData().getAddress() != null) {
				financialPlanner.getPersonalData().getAddress().setId(oldFpl.getPersonalData().getAddress().getId());
			}
			if(oldFpl.getPersonalData().getPermanentAddress() != null && financialPlanner.getPersonalData().getPermanentAddress() != null) {
				financialPlanner.getPersonalData().getPermanentAddress().setId(oldFpl.getPersonalData().getPermanentAddress().getId());
    		}
			financialPlanner.setUpdationDate(new Date());
		}
		
		
		financialPlannerDAO.saveOrUpdate(financialPlanner, true);
		if(oldFpl != null) {
			if(oldFpl.getPersonalData().getAddress() != null && financialPlanner.getPersonalData().getAddress() == null) {
	    		addressDAO.delete(oldFpl.getPersonalData().getAddress(), true);
	    	}
	    	if(oldFpl.getPersonalData().getPermanentAddress() != null && financialPlanner.getPersonalData().getPermanentAddress() == null) {
	    		addressDAO.delete(oldFpl.getPersonalData().getPermanentAddress(), true);
	    	}
		}
	}

	@Override
	public void activateProfile(final Long loginId) {
		final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(loginId);
		financialPlanner.setStatus(ProfileStatus.VERIFIED.getStatus());
		financialPlanner.setState(ProfileState.ACTIVE.getValue());
		financialPlannerDAO.update(financialPlanner, true);
	}
	
	@Override
	public void deActivateProfile(final Long loginId) {
		final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(loginId);
		financialPlanner.setState(ProfileState.DEACTIVE.getValue());
		financialPlannerDAO.update(financialPlanner, true);
	}
	
	/**
	 * @param financialPlannerDAO the financialPlannerDAO to set
	 */
	public void setFinancialPlannerDAO(final IFinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(final IValidator<FinancialPlannerPV> validator) {
		this.validator = validator;
	}

	/**
	 * @param fplTransformer the fplTransformer to set
	 */
	public void setFplTransformer(final AbstractTransformer<FinancialPlannerPV, FinancialPlanner> fplTransformer) {
		this.fplTransformer = fplTransformer;
	}

	/**
	 * @param addressDAO the addressDAO to set
	 */
	public void setAddressDAO(final IAddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
}
