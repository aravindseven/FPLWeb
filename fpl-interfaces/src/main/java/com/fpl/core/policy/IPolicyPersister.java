package com.fpl.core.policy;

import com.fpl.policy.NewPolicyParam;
import com.fpl.policy.NewPolicyParam2;

public interface IPolicyPersister {

	void persistPolicy(NewPolicyParam policyParam);
	
	void updatePolicy(String policyId, String status);

	void persistPolicy(NewPolicyParam2 policyParam);
}
