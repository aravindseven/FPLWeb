package com.fpl.core.request.customer;

import java.util.Date;
import java.util.Set;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.request.IRequestManager;
import com.fpl.core.request.RespondRequestPV;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.request.Request;
import com.fpl.request.RequestFplConfig;
import com.fpl.request.RequestStatusReference;
import com.fpl.util.CollectionUtil;
import com.fpl.util.CollectionUtil.IPredicate;

public class CustomerRequestManager implements IRequestManager {

	private IRequestDAO requestDAO;
	private AbstractTransformer<RespondRequestPV, RequestStatusReference> transformer;
	
	@Override
	public void respondRequest(final RespondRequestPV respondRequest) {
		final RequestStatusReference requestStatusRef = transformer.transform(respondRequest);
		final Request request = requestDAO.get(requestStatusRef.getRequestId());
		final Set<RequestFplConfig> fplConfigs = request.getRequestFplConfigList();
		final RequestFplConfig requestFplConfig = CollectionUtil.find(fplConfigs, new IPredicate<RequestFplConfig>() {
			@Override
			public boolean evaluate(final RequestFplConfig requestFplConfig) {
				return requestFplConfig.getFinPlannerId().equals(requestStatusRef.getFinPlannerId());
			}
		});
		requestFplConfig.setStatusId(requestStatusRef.getStatusId());
		request.getRequestReferenceList().add(requestStatusRef);
		request.setCurrentStatusId(requestStatusRef.getStatusId());
		request.setUpdatedDate(new Date());
		requestDAO.update(request, true);
		//TODO: add the mail sending to fpl
	}

	/**
	 * @param requestDAO the requestDAO to set
	 */
	public void setRequestDAO(final IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(
			final AbstractTransformer<RespondRequestPV, RequestStatusReference> transformer) {
		this.transformer = transformer;
	}
}
