package com.fpl.core.profile;


import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.profile.personal.PersonalData;


public interface IpersonalDAO extends IHibernateDAOSupport<PersonalData> {
	String getinfo(String fname,String lname,String email,String secans);

	String getpasswordinfo(String fname, String lname, String email, String secans); 
 
	PersonalData getuserdata(final String email);
}
