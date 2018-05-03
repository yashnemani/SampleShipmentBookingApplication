package com.banyan.FullLoadRequest.RepoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.banyan.FullLoadRequest.Repos.RateQtDetailRepositoryCustom;

@Repository
public class RateQtDetailRepositoryImpl implements RateQtDetailRepositoryCustom {

	@Autowired
	DataSource ds;
	@Autowired
	JdbcTemplate jdbc;

	public void setJdbcTemplate(DataSource ds) {

		this.jdbc = new JdbcTemplate(ds);
	}

	@Override
	public Integer findBanPkgType(int id) {

		setJdbcTemplate(ds);
		String sql = "select b.banyan_pkg_type from banyan_pkg_type_lookup b,rate_quote_detail r where r.rt_qte_add_id=? and b.fc_pkg_type=r.piece_type";

		PreparedStatementSetter prep = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement prepStatement) throws SQLException {

				prepStatement.setInt(1, id);
			}
		};

		List<Integer> PkgTypes = new ArrayList<>();
		PkgTypes = jdbc.query(sql, prep, new PkgTypeMapper());
		Integer PkgType = null;
		if (PkgTypes.size() != 0)
			PkgType = PkgTypes.get(0);

		return PkgType;
	}

	private static class PkgTypeMapper implements RowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {

			Integer PkgType = rs.getInt(1);
			return PkgType;
		}

	}
	
}
