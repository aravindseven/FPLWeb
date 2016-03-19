package com.fpl.persistence.financialplanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.SearchRequestParam;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.request.RequestFplConfig;
import com.fpl.status.Status;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

public class FPLRequestQueryDAO implements IFPLRequestQueryDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PartialUserView> getFPListByLocation(final NewRequestPV newRequest) {
		final StringBuilder query = new StringBuilder();
//		query.append("select fp_id, pd_name, pd_lname_rnumber, ls_online, ad.ad_city ");
//		query.append("  from fpl_ma_financial_planner fp, fpl_ma_personal_data pd, fpl_ma_login_support ls, fpl_tr_config_finplan_dom fdc, fpl_ma_address ad ");
//		query.append(" where fp.fp_pd_id =  pd.pd_id ");
//		query.append("  and fp.fp_lc_id = ls.ls_lc_id ");
//		query.append("  and fp.fp_id = fdc.fd_fp_id ");
//		query.append("  and pd.pd_cu_ad_id = ad.ad_id ");
//		/*query.append("  and ad.ad_postal_code = ? ");*/
//		query.append("  and fdc.fd_do_id = ? ");
//		query.append(" order by ls_online, ad_postal_code ");
		
		query.append("select fp_id, ");
		query.append("CASE FP_UT_ID");
		query.append("   when 4 then pd.pd_name");
		query.append("   when 3 then sd.pd_name ");
		query.append("END as firstName,");
		query.append("(CASE FP_UT_ID ");
		query.append(" when 4 then pd.pd_lname_rnumber");
		query.append(" when 3 then sd.pd_lname_rnumber");
		query.append(" END) as lastName");
		query.append(", ls_online, ad.ad_street ,");
			
		query.append("( 3959 * acos( cos( radians('"+newRequest.getLatitude().toString()+
				"') ) * cos(radians( AD_LATITUDE ) ) ");
		query.append("* cos( radians( AD_LONGITUDE ) - radians('"+newRequest.getLongitude().toString()+
				"') )");
		query.append("+ sin( radians ('"+newRequest.getLatitude().toString()+
				"') ) * sin( radians( AD_LATITUDE ) ) ) ) AS distance");

		query.append(" from fpl_ma_financial_planner fp, fpl_ma_personal_data pd,fpl_ma_personal_data sd,"); 
		query.append("fpl_ma_login_support ls, fpl_tr_config_finplan_dom fdc, ");
		query.append("fpl_ma_address ad  where fp.fp_pd_id =  pd.pd_id   and ");
		query.append("fp.fp_secondary_pd_id=sd.pd_id and ");
		query.append("fp.fp_lc_id = ls.ls_lc_id   and fp.fp_id = fdc.fd_fp_id ");  
		query.append("and pd.pd_cu_ad_id = ad.ad_id   ");
		if(StringUtil.isNotEmpty(newRequest.getLocation()))
		{
			query.append("and ad.ad_street like ? ");
		}
		query.append("and fdc.fd_do_id = ?  ");
		query.append(" HAVING distance < 10 ORDER BY distance,"+
				" ls_online, ad_postal_code"); 
		
		System.out.println(query);
		Object[] params=null;
		if(StringUtil.isNotEmpty(newRequest.getLocation()))
		{
			 params = new Object[] {"%"+newRequest.getLocation()+"%",Long.valueOf(newRequest.getType())};
		}else
		{
			 params = new Object[] { Long.valueOf(newRequest.getType())};
		}
		return jdbcTemplate.query(query.toString(), params, new RowMapper<PartialUserView>() {
			@Override
			public PartialUserView mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final PartialUserView partialFPLView = new PartialUserView();
				partialFPLView.setId(resultSet.getLong("fp_id")+"");
				partialFPLView.setFirstName(resultSet.getString("firstName"));
				partialFPLView.setLastName(resultSet.getString("lastName"));
				partialFPLView.setLocation(resultSet.getString("ad_street"));
				final int online = resultSet.getInt("ls_online");
				partialFPLView.setOnline((online == 0));
				return partialFPLView;
			}
		});
	}
	
	@Override
	public Collection<RequestFplConfig> getRequestFplConfigList(final SearchRequestParam requestParam) {
		final StringBuilder query = new StringBuilder();
		query.append(" select re_id, st_description, st_dis_desc  ");
		query.append("   from fpl_tr_request re, fpl_tr_request_fpl_config frc, fpl_ma_status st ");
		query.append("  where re.re_id = frc.rf_re_id ");
		query.append("    and frc.rf_status = st.st_id ");
		query.append("    and frc.rf_fp_id = ? ");
		if(StringUtil.isNotEmpty(requestParam.getFromDate())) {
			query.append("    and re.re_created_date between ? and ? ");
		}
		if(StringUtil.isNotEmpty(requestParam.getType()) && Long.parseLong(requestParam.getType())>0) {
			query.append("    and re.re_dm_id = ? ");
		}
		if(StringUtil.isNotEmpty(requestParam.getStatus())  && Long.parseLong(requestParam.getStatus())>0) {
			query.append("    and re.re_current_status = ? ");
		}
		if(StringUtil.isNotEmpty(requestParam.getRequestId())) {
			query.append("    and re.re_id = ? ");
		}
		return jdbcTemplate.query(query.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement statement) throws SQLException {
				int index = 1;
				statement.setLong(index++, Long.valueOf(requestParam.getId()));
				if(StringUtil.isNotEmpty(requestParam.getFromDate())) {
					final Date fromDate = DateUtil.getFormattedDate(requestParam.getFromDate(), "dd/MM/yyyy");
					
					Date toDate;
					if(StringUtil.isNotEmpty(requestParam.getToDate()))
					{	
						toDate= DateUtil.getFormattedDate(requestParam.getToDate(), "dd/MM/yyyy");
					}else
					{
						toDate=new Date();
					}
					
					statement.setDate(index++, new java.sql.Date(fromDate.getTime()));
					statement.setDate(index++, new java.sql.Date(toDate.getTime()));
				}
				if(StringUtil.isNotEmpty(requestParam.getType()) && Long.parseLong(requestParam.getType())>0) {
					statement.setLong(index++, Long.valueOf(requestParam.getType()));
				}
				if(StringUtil.isNotEmpty(requestParam.getStatus()) && Long.parseLong(requestParam.getStatus())>0) {
					statement.setLong(index++, Long.valueOf(requestParam.getStatus()));
				}
				if(StringUtil.isNotEmpty(requestParam.getRequestId())) {
					statement.setLong(index++, Long.valueOf(requestParam.getRequestId()));
				}
			}
		}, new RowMapper<RequestFplConfig>() {
			@Override
			public RequestFplConfig mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final RequestFplConfig requestFplConfig = new RequestFplConfig();
				requestFplConfig.setRequestId(resultSet.getLong("re_id"));
				final Status status = new Status();
				status.setDisDescription(resultSet.getString("st_dis_desc"));
				status.setDescription(resultSet.getString("st_description"));
				requestFplConfig.setStatus(status);
				return requestFplConfig;
			}
		});
	}
	
	@Override
	public Collection<PartialUserView> getFPLCollection(final FPLSearchParam searchParam) {
		final StringBuilder query = new StringBuilder();
		query.append("select fp_id, pd_name, pd_lname_rnumber, ls_online, ad.ad_city ");
		query.append("  from fpl_ma_financial_planner fp, fpl_ma_personal_data pd, fpl_ma_login_support ls, fpl_tr_config_finplan_dom fdc, fpl_ma_address ad ");
		query.append(" where fp.fp_pd_id =  pd.pd_id ");
		query.append("  and fp.fp_lc_id = ls.ls_lc_id ");
		query.append("  and fp.fp_id = fdc.fd_fp_id ");
		query.append("  and pd.pd_cu_ad_id = ad.ad_id ");
		if(searchParam != null && StringUtil.isNotEmpty(searchParam.getPostalCode())) {
			query.append("  and ad.ad_postal_code = ? ");
		}
		if(searchParam != null && StringUtil.isNotEmpty(searchParam.getType())) {
			query.append("  and fdc.fd_do_id = ? ");
		}
		query.append(" order by ls_online, ad_postal_code ");
		return jdbcTemplate.query(query.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement statement) throws SQLException {
				int index = 1;
				if(searchParam != null && StringUtil.isNotEmpty(searchParam.getPostalCode())) {
					statement.setString(index++, searchParam.getPostalCode());
				}
				if(searchParam != null && StringUtil.isNotEmpty(searchParam.getType())) {
					statement.setLong(index++, Long.valueOf(searchParam.getType()));
				}
			}
		}, new RowMapper<PartialUserView>() {
			@Override
			public PartialUserView mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final PartialUserView partialFPLView = new PartialUserView();
				partialFPLView.setId(resultSet.getLong("fp_id")+"");
				partialFPLView.setFirstName(resultSet.getString("pd_name"));
				partialFPLView.setLastName(resultSet.getString("pd_lname_rnumber"));
				partialFPLView.setLocation(resultSet.getString("ad_city"));
				final int online = resultSet.getInt("ls_online");
				partialFPLView.setOnline((online == 0));
				return partialFPLView;
			}
		});
	}
	
	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
