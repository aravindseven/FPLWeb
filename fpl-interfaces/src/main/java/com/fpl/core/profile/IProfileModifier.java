package com.fpl.core.profile;

public interface IProfileModifier {

	void activateProfile(Long loginId);
	
	void deActivateProfile(Long loginId);
}
