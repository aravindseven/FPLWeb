package com.fpl.persistence.login;

import com.fpl.login.entity.User;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface IUserDAO extends IHibernateDAOSupport<User> {

	User getUser(final String userType);
}
