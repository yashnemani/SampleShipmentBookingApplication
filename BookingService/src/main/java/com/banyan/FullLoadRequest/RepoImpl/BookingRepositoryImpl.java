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

		String sql_1 = "update booking_queue set last_timestamp=? where id = 0";
		Timestamp timestamp_new = new Timestamp(System.currentTimeMillis());

		PreparedStatementSetter prep1 = new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setTimestamp(1, timestamp_new);
			}
		};

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

		int n = jdbc.update(sql_1, prep1);
		System.out.println("Update timestamp: " + n);
	}

	@Override
	public void insertIntoUpdateQueue() {

		setJdbcTemplate(ds);
		String sql = "insert into banyan_update_queue(booking_id,pro) select rt_qte_id,pro_no from rate_quote_address where rt_qte_id in "
				+ "(select booking_id from booking where provider_id=0 and booking_id not in (select booking_id from booking_reference where reference_type=0)) and pro_no is not null";
		int rows = jdbc.update(sql);
		System.out.println("Rows Updated in Update Queue: " + rows);

	}

	@Override
	public void insertNewBookingReferences() {

		setJdbcTemplate(ds);
		String sql = "insert into booking_reference(booking_id,reference_type,reference) select booking_id,0,pro from banyan_update_queue "
				+ "where booking_id not in(select booking_id from booking_reference where reference_type=0)";
		int rows = jdbc.update(sql);
		System.out.println("Number of Booking References updated: " + rows);

	}

	@Override
	public void clearUpdateQueue() {

		setJdbcTemplate(ds);
		String sql = "delete from banyan_update_queue";
		int rows = jdbc.update(sql);
		System.out.println("Number of rows deleted from Update Queue " + rows);
	}

	@Override
	public void addToUpdateQueue(Integer rateId, String pro) {
		setJdbcTemplate(ds);
		String sql = "insert into banyan_update_queue(booking_id,pro) values(?,?)";
		PreparedStatementSetter prep = new PreparedStatementSetter() {
			@Override
			public void setValues(java.sql.PreparedStatement prepstatement) throws SQLException {
				prepstatement.setInt(1, rateId);
				prepstatement.setString(2, pro);
			}
		};
		int rows = jdbc.update(sql, prep);
		System.out.println("Number of rows inserted from Update Queue " + rows);
	}
}
