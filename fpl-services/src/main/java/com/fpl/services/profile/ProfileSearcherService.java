package com.fpl.services.profile;

import java.util.Collection;

import com.fpl.core.profile.admin.IProfileSearcher;
import com.fpl.profile.admin.ProfileSearchResultPV;
import com.fpl.profile.admin.SearchProfilePV;

public class ProfileSearcherService implements IProfileSearcherService {

	private IProfileSearcher profileSearcher;

	@Override
	public Collection<ProfileSearchResultPV> getProfileList(final SearchProfilePV searchProfile) {
		return profileSearcher.getProfileList(searchProfile);
	}

	/**
	 * @param profileSearcher the profileSearcher to set
	 */
	public void setProfileSearcher(final IProfileSearcher profileSearcher) {
		this.profileSearcher = profileSearcher;
	}
}
