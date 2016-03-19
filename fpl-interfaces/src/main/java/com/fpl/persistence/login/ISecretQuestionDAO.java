package com.fpl.persistence.login;

import com.fpl.login.entity.SecretQuestion;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface ISecretQuestionDAO extends IHibernateDAOSupport<SecretQuestion> {

	SecretQuestion getSecretQuestion(String question);
}
