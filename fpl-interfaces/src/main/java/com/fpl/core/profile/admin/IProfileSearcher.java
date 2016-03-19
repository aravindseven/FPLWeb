package com.fpl.core.profile.admin;

import java.util.Collection;

import com.fpl.profile.admin.ProfileSearchResultPV;
import com.fpl.profile.admin.SearchProfilePV;

public interface IProfileSearcher {

	Collection<ProfileSearchResultPV> getProfileList(SearchProfilePV searchProfile);
}
