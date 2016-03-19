package com.fpl.core.profile.admin;

import java.util.Map;

import com.fpl.core.profile.IProfileModifier;
import com.fpl.login.entity.LoginCredential;
import com.fpl.persistence.login.ILoginCredentialDAO;

public class AdminProfileManager implements IAdminProfileManager {
	
	private ILoginCredentialDAO loginCredentialDAO;
	private Map<String, IProfileModifier> profilePersisterMap;
	
	@Override
	public void activateProfile(final String email) {
		final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(email);
		final String user = credential.getUser().getUserType();
		System.out.println("==== user ===="+ user);
		final IProfileModifier profileModifier = profilePersisterMap.get(user);
		if(profileModifier != null) {
			profileModifier.activateProfile(credential.getId());
		}
	}
	
	@Override
	public void deActivateProfile(final String email) {
		final LoginCredential credential = loginCredentialDAO.getLoginCredentialByMail(email);
		final String user = credential.getUser().getUserType();
		System.out.println("==== user ===="+ user);
		final IProfileModifier profileModifier = profilePersisterMap.get(user);
		if(profileModifier != null) {
			profileModifier.deActivateProfile(credential.getId());
		}
	}
	
	/**
	 * @param loginCredentialDAO the loginCredentialDAO to set
	 */
	public void setLoginCredentialDAO(final ILoginCredentialDAO loginCredentialDAO) {
		this.loginCredentialDAO = loginCredentialDAO;
	}

	/**
	 * @param profilePersisterMap the profilePersisterMap to set
	 */
	public void setProfilePersisterMap(
			final Map<String, IProfileModifier> profilePersisterMap) {
		this.profilePersisterMap = profilePersisterMap;
	}
}
