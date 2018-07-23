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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Component
@Table(name = "Booking_Status", schema = "TBB")
public class BookingStatus implements Comparable<BookingStatus> {

	@Id
	@Column(name = "Booking_Status_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public BookingStatus() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingStatus other = (BookingStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@Column(name = "Status_Id")
	private String status;

	@Column(name = "Status_Date")
	private java.sql.Timestamp date;

	@Column(name = "Message")
	private String message;

	@Column(name = "Location")
	private String location;
	
	@Column(name="state")
	private String state;
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Timestamp getDate() {
		return date;
	}

	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int compareTo(BookingStatus obj) {
		if (this.id > obj.getId())
			return 1;
		else if (this.id < obj.getId())
			return -1;
		else
			return 0;
	}

	@JsonInclude()
	@Transient
	private String date_graph;

	public String getDate_graph() {
		return date_graph;
	}

	public void setDate_graph() {
		Timestamp dateTime = this.getDate();
		LocalDateTime c = null;
		if (dateTime != null) {
			c = dateTime.toLocalDateTime();
			this.date_graph = c.toString();
		}
	}

}
