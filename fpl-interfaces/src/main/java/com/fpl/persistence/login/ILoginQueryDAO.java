package com.fpl.persistence.login;

import com.fpl.core.communication.UserInfo;
import com.fpl.login.UserType;

public interface ILoginQueryDAO {

	UserInfo getUserInfo(UserType userType, Long custOrFplId);
}
