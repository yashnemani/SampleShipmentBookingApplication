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

import com.banyan.FullLoadRequest.Repos.RateQtAddressRepositoryCustom;
import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Booking.Contact;

@Repository
public class RateQtAddressRepositoryImpl implements RateQtAddressRepositoryCustom {

	@Autowired
	DataSource ds;
	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	Address address;
	@Autowired
	Contact contact;


	public void setJdbcTemplate(DataSource ds) {

		this.jdbc = new JdbcTemplate(ds);
	}

	@Override
	public Address findSiteAddress(int locNum, int clientCode) {

		setJdbcTemplate(ds);
		String sql = "select sa.address,sa.city,sa.country_code,sa.state_code,sa.zip_code from site_address sa,site_address_types st where sa.site_loc_number in (0,?) and sa.site_client_code=? and sa.active='Y' and st.site_addr_id=sa.id and st.address_type='I'";

		PreparedStatementSetter prep = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement prepStatement) throws SQLException {

				prepStatement.setInt(1, locNum);
				prepStatement.setInt(2, clientCode);
			}
		};

		List<Address> addresses = new ArrayList<>();
		addresses = jdbc.query(sql, prep, new AddressMapper());
		if (addresses.size() != 0)
			address = addresses.get(0);

		return address;
	}

	private static class AddressMapper implements RowMapper<Address> {

		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {

			Address add = new Address.Builder().setAddress1(rs.getString(1)).setAddress2(null).setCity(rs.getString(2))
					.setCountryCode(rs.getString(3)).setCountryName(rs.getString(3)).setLocationName(rs.getString(2))
					.setState(rs.getString(4)).setZipcode(rs.getString(5)).build();

			return add;

		}

	}

	@Override
	public Contact findSiteContact(int siteCode, int locNum) {

		setJdbcTemplate(ds);
		String sql = "select first_name,last_name,phone_no,phone_ext,e_mail,fax_no from site_contact where site_loc_number=? and site_client_code=?";

		PreparedStatementSetter prep = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement prepStatement) throws SQLException {

				prepStatement.setInt(2, siteCode);
				prepStatement.setInt(1, locNum);
				;
			}
		};

		List<Contact> contacts = new ArrayList<>();
		contacts = jdbc.query(sql, prep, new ContactMapper());
		if (contacts.size() != 0)
			contact = contacts.get(0);

		return contact;
	}

	private static class ContactMapper implements RowMapper<Contact> {

		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

			Contact con = new Contact.Builder().firstNameIs(rs.getString(1)).lastNameIs(rs.getString(2))
					.contactNameIs(rs.getString(2)).phoneIs(rs.getString(3)).phoneExtIs(rs.getString(4))
					.emailIs(rs.getString(5)).faxIs(rs.getString(6)).build();

			return con;
		}

	}

}
