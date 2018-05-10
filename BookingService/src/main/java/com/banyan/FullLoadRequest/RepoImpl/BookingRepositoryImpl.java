package com.banyan.FullLoadRequest.RepoImpl;

import java.sql.SQLException;

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
}
