package com.fpl.core.request.financialplanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.RequestListPV;
import com.fpl.core.request.SearchRequestParam;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.core.request.support.AbstractRequestFinder;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.financialplanner.IFPLRequestQueryDAO;
import com.fpl.persistence.request.IRequestFplConfigDAO;
import com.fpl.request.Request;
import com.fpl.request.RequestFplConfig;
import com.fpl.util.CollectionUtil;
import com.fpl.util.CollectionUtil.IPredicate;
import com.fpl.util.DateUtil;

public class RequestFPLFinder extends AbstractRequestFinder implements IRequestFPLFinder {
	
	private IFPLRequestQueryDAO fplRequestQueryDAO;
	private IRequestFplConfigDAO requestFplConfigDAO;
	
	@Override
	public Collection<PartialUserView> getFPListByLocation(final NewRequestPV newRequest) {
		final Collection<PartialUserView> returnFplList = new ArrayList<PartialUserView>();
		final List<PartialUserView> partialFPLViews = fplRequestQueryDAO.getFPListByLocation(newRequest);
//		final IPredicate<PartialUserView> predicate = new IPredicate<PartialUserView>() {
//			@Override
//			public boolean evaluate(final PartialUserView partialFPLView) {
//				boolean flag=false;
//				if(partialFPLView.getLocation()!=null && newRequest.getLocation()!=null)
//				{
//					flag=partialFPLView.getLocation().equals(newRequest.getLocation());
//				}  
//				
//			  return flag;	
//			}
//		};
//		final Collection<PartialUserView> fplList = CollectionUtil.select(partialFPLViews, predicate);
//		returnFplList.addAll(fplList);
//		final Collection<PartialUserView> fplNonLocaionList = CollectionUtil.selectRejected(partialFPLViews, predicate);
//		returnFplList.addAll(fplNonLocaionList);
		return partialFPLViews;
	}

	@Override
	public Collection<RequestListPV> getRequestList(final Long loginId) {
		final Collection<RequestListPV> requestListPVs = new ArrayList<RequestListPV>();
		final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(loginId);
		final Collection<RequestFplConfig> requestFplConfigList = requestFplConfigDAO.getRequestFplConfigList(financialPlanner.getId());
		if(requestFplConfigList != null) {
			for(final RequestFplConfig requestFplConfig : requestFplConfigList) {
				final Request request = requestDAO.get(requestFplConfig.getRequestId());
				final RequestListPV requestListPV = new AbstractTransformer<Request, RequestListPV>() {
					@Override
					public RequestListPV transform(final Request request) {
						final RequestListPV requestListPV = new RequestListPV();
						requestListPV.setId(request.getId()+"");
						requestListPV.setCreatedDate(request.getCreatedDate());
						requestListPV.setKeyword(request.getKeyword());
						requestListPV.setCurrentStatus(request.getStatus().getDescription());
						requestListPV.setStatusDisDesc(requestFplConfig.getStatus().getDisDescription());
						requestListPV.setDescription(requestFplConfig.getStatus().getDescription());
						requestListPV.setDomainName(request.getDomain().getName());
						requestListPV.setLocation(request.getLocation());
						requestListPV.setUpdatedDate(DateUtil.getFormattedDate(request.getUpdatedDate()));
						return requestListPV;
					}
				}.transform(request);
				requestListPVs.add(requestListPV);
			}
		}
		return requestListPVs;
	}
	
	@Override
	public Collection<RequestListPV> getRequestList(final SearchRequestParam searchRequest) {
		final Collection<RequestListPV> requestListPVs = new ArrayList<RequestListPV>();
		final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(Long.valueOf(searchRequest.getId()));
		searchRequest.setId(financialPlanner.getId()+"");
		final Collection<RequestFplConfig> requestFplConfigList = fplRequestQueryDAO.getRequestFplConfigList(searchRequest);
		if(requestFplConfigList != null) {
			for(final RequestFplConfig requestFplConfig : requestFplConfigList) {
				final Request request = requestDAO.get(requestFplConfig.getRequestId());
				final RequestListPV requestListPV = new AbstractTransformer<Request, RequestListPV>() {
					@Override
					public RequestListPV transform(final Request request) {
						final RequestListPV requestListPV = new RequestListPV();
						requestListPV.setId(request.getId()+"");
						requestListPV.setCreatedDate(request.getCreatedDate());
						requestListPV.setCurrentStatus(request.getStatus().getDescription());
						requestListPV.setStatusDisDesc(requestFplConfig.getStatus().getDisDescription());
						requestListPV.setDescription(requestFplConfig.getStatus().getDescription());
						requestListPV.setDomainName(request.getDomain().getName());
						requestListPV.setLocation(request.getLocation());
						requestListPV.setUpdatedDate(DateUtil.getFormattedDate(request.getUpdatedDate()));
						return requestListPV;
					}
				}.transform(request);
				requestListPVs.add(requestListPV);
			}
		}
		return requestListPVs;
	}
	
	/**
	 * @param requestFplConfigDAO the requestFplConfigDAO to set
	 */
	public void setRequestFplConfigDAO(final IRequestFplConfigDAO requestFplConfigDAO) {
		this.requestFplConfigDAO = requestFplConfigDAO;
	}

	/**
	 * @param fplRequestQueryDAO the fplRequestQueryDAO to set
	 */
	public void setFplRequestQueryDAO(final IFPLRequestQueryDAO fplRequestQueryDAO) {
		this.fplRequestQueryDAO = fplRequestQueryDAO;
	}
}
