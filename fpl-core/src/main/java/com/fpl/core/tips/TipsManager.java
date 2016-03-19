package com.fpl.core.tips;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserType;
import com.fpl.persistence.communication.ICommunicationQueryDAO;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.persistence.tips.ITipsDAO;
import com.fpl.profile.customer.Customer;

public class TipsManager implements ITipsManager {

	private ITipsDAO tipsDAO;

	@Override
	public Boolean SaveTips(String admin_tips) {
		System.out.println("TIPS MANAGER CALLED");
		// TODO Auto-generated method stub
		return tipsDAO.SaveTips(admin_tips);
	}

}
