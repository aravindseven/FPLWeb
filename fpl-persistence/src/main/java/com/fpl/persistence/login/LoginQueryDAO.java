package com.fpl.persistence.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fpl.core.communication.UserInfo;
import com.fpl.login.UserType;

public class LoginQueryDAO implements ILoginQueryDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserInfo getUserInfo(final UserType userType, final Long custOrFplId) {
		final StringBuilder query = new StringBuilder();
		query.append(" select lc.lc_id, lc.lc_mail_id, lc.lc_alternate_maill_id, pd.pd_name, pd.pd_lname_rnumber, ls_online, ut.ut_descrption ");
		query.append("   from fpl_ma_login_credential lc, fpl_ma_login_support ls, fpl_ma_user_type ut, fpl_ma_personal_data pd ");
		if(userType.getUser().startsWith("cust")) {
			query.append(" , fpl_ma_customer cu ");
		} else {
			query.append(" , fpl_ma_financial_planner fp ");
		}
		query.append(" where lc.lc_id = ls.ls_lc_id ");
		query.append("  and lc.lc_ut_id = ut.ut_id ");
		if(userType.getUser().startsWith("cust")) {
			query.append("  and lc.lc_id = cu.cu_lc_id ");
			query.append("  and cu.cu_pd_id =  pd.pd_id ");
			query.append("  and cu.cu_id = ? ");
		} else {
			query.append("  and lc.lc_id = fp.fp_lc_id ");
			query.append("  and fp.fp_pd_id = pd.pd_id ");
			query.append("  and fp.fp_id = ? ");
		}
		System.out.println(query);
		final Object[] params = new Object[] {custOrFplId};
		return jdbcTemplate.queryForObject(query.toString(), params, new RowMapper<UserInfo>() {
			@Override
			public UserInfo mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final UserInfo userInfo = new UserInfo();
				userInfo.setLoginId(resultSet.getLong("lc_id"));
				userInfo.setUserType(UserType.valueOf(resultSet.getString("ut_descrption").toUpperCase()));
				userInfo.setEmail(resultSet.getString("lc_mail_id"));
				userInfo.setAlternateEmail(resultSet.getString("lc_alternate_maill_id"));
				final boolean isOnline = resultSet.getInt("ls_online") == 1 ? false : true;
				userInfo.setOnline(isOnline);
				userInfo.setFirstName(resultSet.getString("pd_name"));
				userInfo.setLastName(resultSet.getString("pd_lname_rnumber"));
				return userInfo;
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
