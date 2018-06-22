package com.banyan.FullLoadRequest.Entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stopoff", schema = "TBB")
public class StopOff {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "rt_Qte_Add_Id")
	private Integer rtQteAddId;
	@Column(name = "stop_order")
	private Integer stop_order;
	@Column(name = "type_code")
	private String type_code;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "state_code")
	private String state;
	@Column(name = "zip_code")
	private String zip;
	@Column(name = "country_code")
	private String country;
	@Column(name = "contact_name")
	private String contact_name;
	@Column(name = "contact_phone")
	private String phone;
	@Column(name = "contact_ext")
	private String extension;
	@Column(name = "contact_email")
	private String email;
	@Column(name = "earliest_dt")
	private Timestamp earliest_dt;
	@Column(name = "latest_dt")
	private Timestamp latest_dt;
	@Column(name = "distance")
	private double distance;
	@Column(name = "picking_Up")
	private String pickingUp;
	@Column(name = "dropping_Off")
	private String droppingOff;
	@Column(name = "user_id")
	private String user_id;
	@Column(name = "dt_updated")
	private Timestamp dt_updated;

	public StopOff() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRt_qte_add_id() {
		return rtQteAddId;
	}

	public void setRt_qte_add_id(Integer rtQteAddId) {
		this.rtQteAddId = rtQteAddId;
	}

	public Integer getStop_order() {
		return stop_order;
	}

	public void setStop_order(Integer stop_order) {
		this.stop_order = stop_order;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEarliest_dt() {
		return earliest_dt.toLocalDateTime().toString() + ":00";
	}

	public void setEarliest_dt(Timestamp earliest_dt) {
		this.earliest_dt = earliest_dt;
	}

	public String getLatest_dt() {
		return latest_dt.toLocalDateTime().toString() + ":00";
	}

	public void setLatest_dt(Timestamp latest_dt) {
		this.latest_dt = latest_dt;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getPickingUp() {
		return pickingUp;
	}

	public void setPickingUp(String pickingUp) {
		this.pickingUp = pickingUp;
	}

	public String getDroppingOff() {
		return droppingOff;
	}

	public void setDroppingOff(String droppingOff) {
		this.droppingOff = droppingOff;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Timestamp getDt_updated() {
		return dt_updated;
	}

	public void setDt_updated(Timestamp dt_updated) {
		this.dt_updated = dt_updated;
	}
}
