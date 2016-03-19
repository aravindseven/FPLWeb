package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.profile.admin.AdminProfilePV;

public interface IAdminRegisterService {

	FPLServiceResponse insertAdminProfile(AdminProfilePV adminProfilePV);
}
