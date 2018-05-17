package com.banyan.FullLoadRequest.RepoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepositoryCustom;

@Service
public class BookingRepositoryImpl implements BookingRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	DataSource ds;
	@Autowired
	JdbcTemplate jdbc;

	public void setJdbcTemplate(DataSource ds) {

		this.jdbc = new JdbcTemplate(ds);
	}

	@Transactional
	public void refresh(Booking book) {
		em.refresh(em.merge(book));
	}

	@Override
	public void saveToTrackingQueue(int bookingId, int providerId) {

		setJdbcTemplate(ds);
		String sql = "insert into tracking_queue(booking_id, provider_id) values(?,?)";
		PreparedStatementSetter prep = new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setInt(1, bookingId);
				prepstatement.setInt(2, providerId);
			}
		};

		jdbc.update(sql, prep);
	}

	@Override
	public void deleteFromBookingQueue(int rateId) {

		setJdbcTemplate(ds);
		String sql = "delete from booking_queue where rate_id=?";
		PreparedStatementSetter prep = new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setInt(1, rateId);
			}
		};
		jdbc.update(sql, prep);
	}

	@Override
	public void insertIntoBookingQueue(Timestamp timestamp) {

		setJdbcTemplate(ds);
		
		String sql_1 = "insert into booking_queue(last_timestamp) values(?) where id = 0";
		Timestamp timestamp_new = new Timestamp(System.currentTimeMillis());
		
		PreparedStatementSetter prep1 = new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setTimestamp(1, timestamp_new);
			}
		};

		int n = jdbc.update(sql_1, prep1);
		System.out.println("Update timestamp: " + n);
		
		String sql = "insert into booking_queue(rate_id)"
				+ " select ss.TBB_REFERENCE_NBR from status_summary ss where ss.status_code='SS' and ss.carrier_code in"
				+ " (select  pc.carrier_code from PROVIDER_CARRIER pc where pc.PROVIDER_ID in (0,1,2))"
				+ " and ss.last_updated > ?";

		PreparedStatementSetter prep = new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setTimestamp(1, timestamp);
			}
		};
		int q = jdbc.update(sql, prep);
		System.out.println(q + " rows inserted");




	}
}
