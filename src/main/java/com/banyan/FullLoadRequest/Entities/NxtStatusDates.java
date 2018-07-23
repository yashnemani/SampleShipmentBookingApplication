package com.banyan.FullLoadRequest.Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Booking_Nxt_Status_Dates", schema = "TBB")
public class NxtStatusDates {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bookingId")
	private Booking booking;

	@Column(name = "DT_BOOKED")
	private java.sql.Timestamp dt_booked;

	@Column(name = "DT_PICKED_UP")
	private java.sql.Timestamp dt_pickedup;

	@Column(name = "DT_DELIEVERED")
	private java.sql.Timestamp dt_delivered;

	public NxtStatusDates() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public java.sql.Timestamp getDt_entered() {
		return dt_booked;
	}

	public void setDt_entered(java.sql.Timestamp dt_booked) {
		this.dt_booked = dt_booked;
	}

	public java.sql.Timestamp getDt_pickedup() {
		return dt_pickedup;
	}

	public void setDt_pickedup(java.sql.Timestamp dt_pickedup) {
		this.dt_pickedup = dt_pickedup;
	}

	public java.sql.Timestamp getDt_delivered() {
		return dt_delivered;
	}

	public void setDt_delivered(java.sql.Timestamp dt_delivered) {
		this.dt_delivered = dt_delivered;
	}
	
	@JsonInclude()
	@Transient
	public String gq_dt_booked;
	@JsonInclude()
	@Transient
	public String gq_dt_pickedup;
	@JsonInclude()
	@Transient
	public String gq_dt_delivered;

	public String getGq_dt_booked() {
		return gq_dt_booked;
	}

	public String getGq_dt_pickedup() {
		return gq_dt_pickedup;
	}

	public String getGq_dt_delivered() {
		return gq_dt_delivered;
	}

	public void setGQ_Dates() {
		Timestamp dlvr = this.getDt_delivered();
		Timestamp pkup = this.getDt_pickedup();
		Timestamp bkd = this.getDt_entered();
		LocalDateTime c = null;
		if (dlvr != null) {
			c = dlvr.toLocalDateTime();
			this.gq_dt_delivered = c.toString();
		}
		if (pkup != null) {
			c = pkup.toLocalDateTime();
			this.gq_dt_pickedup = c.toString();
		}
		if (bkd != null) {
			c = bkd.toLocalDateTime();
			this.gq_dt_booked = c.toString();
		}
	}

}
