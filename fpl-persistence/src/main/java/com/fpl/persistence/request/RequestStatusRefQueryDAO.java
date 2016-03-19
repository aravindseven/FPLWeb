package com.fpl.persistence.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.SearchRequestParam;
import com.fpl.core.request.financialplanner.FPLDashboardPV;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.request.Request;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

public class RequestStatusRefQueryDAO implements IRequestStatusRefQueryDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Collection<RequestActivityDTO> getRequestActivityList(final Long requestId) {
		final StringBuilder query = new StringBuilder();
		query.append(" select pd_name, rs_date, st_description ");
		query.append("   from fpl_tr_request_status_ref rsr, fpl_ma_financial_planner fpl, fpl_ma_status st, fpl_ma_personal_data ped ");
		query.append("  where rsr.rs_status = st.st_id ");
		query.append("   and rsr.rs_fp_id = fpl.fp_id ");
		query.append("   and fpl.fp_pd_id = ped.pd_id ");
		query.append("   and rsr.rs_re_id = ? ");
		query.append(" order by rs_date ");
		final Object[] params = new Object[] { requestId };
		return jdbcTemplate.query(query.toString(), params, new RowMapper<RequestActivityDTO>() {
			@Override
			public RequestActivityDTO mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final RequestActivityDTO activityDTO = new RequestActivityDTO();
				activityDTO.setFpName(resultSet.getString("pd_name"));
				final String date = DateUtil.getFormattedDate(resultSet.getDate("rs_date"));
				activityDTO.setDate(date);
				activityDTO.setStatus(resultSet.getString("st_description"));
				return activityDTO;
			}
		});
	}

	@Override
	public Collection<RequestActivityDTO> getRespondedFPL(final Long requestId) {
		final StringBuilder query = new StringBuilder();
		query.append(" select fp_id, pd_name, pd_mobile_phone, re_created_date, re_current_status, rf_date, st_dis_desc, st_description, ad.ad_street ");
		query.append("   from fpl_tr_request re, fpl_tr_request_fpl_config frc, fpl_ma_financial_planner fpl, fpl_ma_status st, fpl_ma_personal_data ped, fpl_ma_address ad ");
		query.append("  where frc.rf_status = st.st_id ");
		query.append("   and frc.rf_fp_id = fpl.fp_id ");
		query.append("   and fpl.fp_pd_id = ped.pd_id ");
		query.append("and ped.pd_cu_ad_id = ad.ad_id   ");
		query.append("   and frc.rf_re_id = re.re_id ");
		query.append("   and frc.rf_re_id = ? ");
		query.append("   and st.st_dis_desc not in (?, ?) ");
		query.append(" order by rf_date ");
		final Object[] params = new Object[] { requestId, "ST_00", "ST_04"};
		return jdbcTemplate.query(query.toString(), params, new RowMapper<RequestActivityDTO>() {
			@Override
			public RequestActivityDTO mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final RequestActivityDTO activityDTO = new RequestActivityDTO();
				activityDTO.setFplId(resultSet.getString("fp_id"));
				activityDTO.setFpName(resultSet.getString("pd_name"));
				activityDTO.setContactNumber(resultSet.getString("pd_mobile_phone"));
				activityDTO.setLocation(resultSet.getString("ad_street"));
				final Date requestCreatedDate = resultSet.getDate("re_created_date");
				activityDTO.setDate(DateUtil.getFormattedDate(requestCreatedDate));
				final Date fplRespondedDate = resultSet.getDate("rf_date");
				activityDTO.setFplRespondedDate(DateUtil.getFormattedDate(fplRespondedDate));
				activityDTO.setStatusDis(resultSet.getString("st_dis_desc"));
				activityDTO.setStatus(resultSet.getString("st_description"));
				activityDTO.setCurrentStatus(resultSet.getString("re_current_status"));
				activityDTO.setDaysDiff(DateUtil.getDateDiff(fplRespondedDate, new Date()));
				return activityDTO;
			}
		});
	}

	
	public Collection<RequestActivityDTO> getRequestDashboardDetails(final Long requestId) {
		final StringBuilder query = new StringBuilder();
		query.append(" select fp_id, pd_name, pd_mobile_phone, re_created_date, re_current_status, rf_date, st.st_dis_desc, st.st_description, ifnull(TIMESTAMPDIFF(HOUR,RF_DATE,CURTIME()),0) as timeDiff  ");
		query.append("   from fpl_tr_request re, fpl_tr_request_fpl_config frc, fpl_ma_financial_planner fpl, fpl_ma_status st, fpl_ma_personal_data ped ");
		query.append("  where frc.rf_status = st.st_id ");
		
		query.append("   and frc.rf_fp_id = fpl.fp_id ");
		query.append("   and fpl.fp_pd_id = ped.pd_id ");
		query.append("   and frc.rf_re_id = re.re_id ");
		query.append("   and frc.rf_re_id = ? ");
		query.append(" and  ( case when st.st_dis_desc='ST_00' THEN ifnull(TIMESTAMPDIFF(HOUR,RF_DATE,CURTIME()),0)<=48 ELSE TRUE END)");
		query.append("   and st.st_dis_desc not in (?) ");
		query.append(" order by rf_date DESC ");
		final Object[] params = new Object[] { requestId, "ST_03"};
		return jdbcTemplate.query(query.toString(), params, new RowMapper<RequestActivityDTO>() {
			@Override
			public RequestActivityDTO mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final RequestActivityDTO activityDTO = new RequestActivityDTO();
				activityDTO.setFplId(resultSet.getString("fp_id"));
				activityDTO.setFpName(resultSet.getString("pd_name"));
				activityDTO.setContactNumber(resultSet.getString("pd_mobile_phone"));
				final Date requestCreatedDate = resultSet.getDate("re_created_date");
				activityDTO.setDate(DateUtil.getFormattedDate(requestCreatedDate));
				final Date fplRespondedDate = resultSet.getDate("rf_date");
				activityDTO.setFplRespondedDate(DateUtil.getFormattedDate(fplRespondedDate));
				activityDTO.setStatusDis(resultSet.getString("st_dis_desc"));
				activityDTO.setStatus(resultSet.getString("st_description"));
				activityDTO.setCurrentStatus(resultSet.getString("re_current_status"));
				activityDTO.setDaysDiff(resultSet.getInt("timeDiff"));
				return activityDTO;
			}
		});
	}


	
	
	public Collection<FPLDashboardPV> getRequestDashboardCustomerDetails(final Long fplId) {
		final StringBuilder query = new StringBuilder();
		
		query.append(" select frc.rf_fp_id,re_id,cu_id, pd_name, pd_mobile_phone, re_created_date, re_current_status, ");
		query.append(" rf_date, st.st_dis_desc, st.st_description,do_name, ifnull(TIMESTAMPDIFF(HOUR,RF_DATE,CURTIME()),0) as timeDiff ");
		query.append(" from fpl_tr_request re, fpl_tr_request_fpl_config frc, "); 
		query.append(" fpl_ma_customer cus, fpl_ma_status st, fpl_ma_status cusst, ");
		query.append(" fpl_ma_personal_data ped, fpl_ma_domain dom ");
		query.append(" where frc.rf_status = st.st_id and  re.re_cu_id = cus.cu_id "); 
		query.append(" and re.re_current_status=cusst.st_id "); 
		query.append(" and cus.cu_pd_id = ped.pd_id and frc.rf_re_id = re.re_id ");
		query.append(" and dom.do_id=re.re_dm_id   and frc.rf_fp_id = ? ");
		query.append(" and  ( case when st.st_dis_desc='ST_00' THEN ifnull(TIMESTAMPDIFF(HOUR,RF_DATE,CURTIME()),0)<=48 ELSE TRUE END)"); 
		query.append(" and st.st_dis_desc not in (?,?) and cusst.st_dis_desc not in ('ST_03')  order by rf_date DESC ");
				
		
		final Object[] params = new Object[] { fplId, "ST_03","ST_08"};
		return jdbcTemplate.query(query.toString(), params, new RowMapper<FPLDashboardPV>() {
			@Override
			public FPLDashboardPV mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				
				FPLDashboardPV dashboardPV=new FPLDashboardPV();
				final Date requestCreatedDate = resultSet.getDate("re_created_date");
				dashboardPV.setCreationDate(requestCreatedDate);
				dashboardPV.setType(resultSet.getString("do_name"));
				dashboardPV.setRequestId(resultSet.getString("re_id"));
				dashboardPV.setCustomerId(resultSet.getString("cu_id"));
				final RequestActivityDTO activityDTO = new RequestActivityDTO();
				activityDTO.setFplId(resultSet.getString("rf_fp_id"));
				activityDTO.setFpName(resultSet.getString("pd_name"));
				activityDTO.setContactNumber(resultSet.getString("pd_mobile_phone"));
				
				activityDTO.setDate(DateUtil.getFormattedDate(requestCreatedDate));
				final Date fplRespondedDate = resultSet.getDate("rf_date");
				activityDTO.setFplRespondedDate(DateUtil.getFormattedDate(fplRespondedDate,"dd-MMM-yyyy"));
				activityDTO.setStatusDis(resultSet.getString("st_dis_desc"));
				activityDTO.setStatus(resultSet.getString("st_description"));
				activityDTO.setCurrentStatus(resultSet.getString("re_current_status"));
				activityDTO.setDaysDiff(resultSet.getInt("timeDiff"));
				dashboardPV.setRequestActivityDTO(activityDTO);
				return dashboardPV;
			}
		});
	}
	
	
//	public Collection<Request> getRequestList(final SearchRequestParam requestParam,final Long fplId) {
//		
//		
//	final StringBuilder query = new StringBuilder();
//		
//		query.append(" select frc.rf_fp_id,re_id,cu_id, pd_name, pd_mobile_phone, re_created_date, re_current_status, ");
//		query.append(" rf_date, st.st_dis_desc, st.st_description,do_name, ifnull(TIMESTAMPDIFF(HOUR,RF_DATE,CURTIME()),0) as timeDiff ");
//		query.append(" from fpl_tr_request re, fpl_tr_request_fpl_config frc, "); 
//		query.append(" fpl_ma_customer cus, fpl_ma_status st, fpl_ma_status cusst, ");
//		query.append(" fpl_ma_personal_data ped, fpl_ma_domain dom ");
//		query.append(" where frc.rf_status = st.st_id and  re.re_cu_id = cus.cu_id "); 
//		query.append(" and re.re_current_status=cusst.st_id "); 
//		query.append(" and cus.cu_pd_id = ped.pd_id and frc.rf_re_id = re.re_id ");
//		query.append(" and dom.do_id=re.re_dm_id   and frc.rf_fp_id = ? ");
//		query.append(" order by rf_date DESC ");
//		final Date fromDate = DateUtil.getFormattedDate(requestParam.getFromDate(), "yyyy-MM-dd");
//		final Date toDate = DateUtil.getFormattedDate(requestParam.getToDate(), "yyyy-MM-dd");
//		criteria.add(Restrictions.between("createdDate", fromDate, toDate));
//	}
//	if(StringUtil.isNotEmpty(requestParam.getRequestId())) {
//		criteria.add(Restrictions.eq("id", Long.valueOf(requestParam.getRequestId())));
//	
//		
//		final Object[] params = new Object[] { fplId, Long.valueOf(requestParam.getId()),Long.valueOf(requestParam.getType()), };
//		return jdbcTemplate.query(query.toString(), params, new RowMapper<FPLDashboardPV>() {
//			@Override
//			public FPLDashboardPV mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
//				
//				FPLDashboardPV dashboardPV=new FPLDashboardPV();
//				final Date requestCreatedDate = resultSet.getDate("re_created_date");
//				dashboardPV.setCreationDate(requestCreatedDate);
//				dashboardPV.setType(resultSet.getString("do_name"));
//				dashboardPV.setRequestId(resultSet.getString("re_id"));
//				dashboardPV.setCustomerId(resultSet.getString("cu_id"));
//				final RequestActivityDTO activityDTO = new RequestActivityDTO();
//				activityDTO.setFplId(resultSet.getString("rf_fp_id"));
//				activityDTO.setFpName(resultSet.getString("pd_name"));
//				activityDTO.setContactNumber(resultSet.getString("pd_mobile_phone"));
//				
//				activityDTO.setDate(DateUtil.getFormattedDate(requestCreatedDate));
//				final Date fplRespondedDate = resultSet.getDate("rf_date");
//				activityDTO.setFplRespondedDate(DateUtil.getFormattedDate(fplRespondedDate,"dd-MMM-yyyy"));
//				activityDTO.setStatusDis(resultSet.getString("st_dis_desc"));
//				activityDTO.setStatus(resultSet.getString("st_description"));
//				activityDTO.setCurrentStatus(resultSet.getString("re_current_status"));
//				activityDTO.setDaysDiff(resultSet.getInt("timeDiff"));
//				dashboardPV.setRequestActivityDTO(activityDTO);
//				return dashboardPV;
//			}
//		});
//		
//		
//		return getHibernateTemplate().execute(new HibernateCallback<Request, Collection<Request>>("getRequestByCustomerId") {
//			@Override
//			public Collection<Request> doInHibernate(final Session session) {
//				final Criteria criteria = session.createCriteria(getEntityClass());
//				criteria.add(Restrictions.eq("customerId", Long.valueOf(requestParam.getId())));
//				if(StringUtil.isNotEmpty(requestParam.getType())) {
//					criteria.add(Restrictions.eq("domainId", Long.valueOf(requestParam.getType())));
//				}
//				if(StringUtil.isNotEmpty(requestParam.getStatus())) {
//					criteria.add(Restrictions.eq("currentStatusId", Long.valueOf(requestParam.getStatus())));
//				}
//				if(StringUtil.isNotEmpty(requestParam.getFromDate())) {
//					final Date fromDate = DateUtil.getFormattedDate(requestParam.getFromDate(), "yyyy-MM-dd");
//					final Date toDate = DateUtil.getFormattedDate(requestParam.getToDate(), "yyyy-MM-dd");
//					criteria.add(Restrictions.between("createdDate", fromDate, toDate));
//				}
//				if(StringUtil.isNotEmpty(requestParam.getRequestId())) {
//					criteria.add(Restrictions.eq("id", Long.valueOf(requestParam.getRequestId())));
//				}
//				criteria.addOrder(Order.asc("createdDate"));
//				return criteria.list();
//			}
//		});
//	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
