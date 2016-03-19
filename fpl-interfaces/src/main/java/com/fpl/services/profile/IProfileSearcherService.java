package com.fpl.services.profile;

import java.util.Collection;

import com.fpl.profile.admin.ProfileSearchResultPV;
import com.fpl.profile.admin.SearchProfilePV;

public interface IProfileSearcherService {

	Collection<ProfileSearchResultPV> getProfileList(SearchProfilePV searchProfile);
}
