package com.fpl.services.profile;

public interface IProfileFetcher {

	<ReturnType> ReturnType getProfilePageView(Long loginId);
	Long getMaxId();
}
