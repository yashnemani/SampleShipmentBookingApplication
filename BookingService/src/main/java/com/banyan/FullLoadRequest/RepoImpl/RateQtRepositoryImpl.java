package com.banyan.FullLoadRequest.RepoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtRepositoryCustom;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;

@Repository
public class RateQtRepositoryImpl implements RateQtRepositoryCustom {

	@Autowired
	DataSource ds;
	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	RateQtAddressRepository addressRep;
	@Autowired
	Loadinfo load;

	Map<String, Object> codeMap = new HashMap<String, Object>();

	public void setJdbcTemplate(DataSource ds) {
		this.jdbc = new JdbcTemplate(ds);
	}

	// Get All FusionCenter Accessorial charge codes for a given rate quote
	@Override
	public List<String> findAllAccCodes(Integer addId) {

		setJdbcTemplate(ds);
		String sql = "select acc_chg_code from rate_quote_acc_charges where rt_qte_add_id=?";

		PreparedStatementSetter prep = new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setInt(1, addId);
			}
		};

		List<String> acc_Codes = new ArrayList<>();
		acc_Codes = jdbc.query(sql, prep, new AccRowMapper());
		System.out.println();
		System.out.print("FusionCenter Access Charge Codes:");
		acc_Codes.forEach(i -> System.out.print(" "+i+","));

		return acc_Codes;
	}

	private static class AccRowMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {

			String acc_Code = new String();
			acc_Code = rs.getString(1);
			return acc_Code;
		}

	}

	// Get Banyan Charge codes for the FusionCenter Accessorial charge codes
	@Override
	public List<String> findBanyanAccCodes(List<String> accCodes) {

		setJdbcTemplate(ds);
		List<String> banyanAccCodes = new ArrayList<>();
		String sql = "select ban_acc_code from banyan_fc_accessorial_lookup where fc_acc_code = ?";

		for (int i = 0; i < accCodes.size(); i++) {
			int j = i;

			PreparedStatementSetter prep = new PreparedStatementSetter() {

				@Override
				public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
					prepstatement.setString(1, accCodes.get(j));
				}
			};

			List<String> code = jdbc.query(sql, prep, new BanyanMapper());
			if (code.size() != 0)
				banyanAccCodes.add(code.get(0));
		}
		System.out.println();
		System.out.print("Banyan Charge Codes: ");
		banyanAccCodes.forEach(i -> System.out.print(" "+i+","));

		return banyanAccCodes;
	}

	private static class BanyanMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {

			String acc_Code = new String();
			acc_Code = rs.getString(1);
			return acc_Code;
		}

	}

	// get ShowClientBOL to build the Banyan BillTo Object
	@Override
	public String findClientBOL(Integer siteClientCode, Integer siteLocNumber, String carrierCode) {

		setJdbcTemplate(ds);
		// Calling GETSHOWCLIENTBOL Function in DB
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbc).withFunctionName("GETSHOWCLIENTBOL").withReturnValue();

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("ClientCode", siteClientCode);
		inParamMap.put("LocNumber", siteLocNumber);
		inParamMap.put("carrierCode", carrierCode);

		// For Future Use
		codeMap = inParamMap;

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println("Show Client BOL: "+simpleJdbcCallResult.get("return"));
		String clientBOL = (String) simpleJdbcCallResult.get("return");

		return clientBOL;
	}

	public Map<String, Object> getSiteCodes() {
		return codeMap;
	}
}
