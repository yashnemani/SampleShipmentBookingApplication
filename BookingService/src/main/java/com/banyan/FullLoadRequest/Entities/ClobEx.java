/*package com.banyan.FullLoadRequest.Entities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;

@Entity
@Table(name = "AA_CLOB_EX", schema = "TBB")
public class ClobEx {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	@Lob
	@Column(name = "BOOKDOC")
	public String fullLoad;

	public ClobEx() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FullLoad_Request getFullLoad() {

		FullLoad_Request obj = null;
		try {
			byte[] load = Base64.getDecoder().decode(this.fullLoad);
			ByteArrayInputStream bi = new ByteArrayInputStream(load);
			ObjectInputStream si = new ObjectInputStream(bi);
			obj = (FullLoad_Request) si.readObject();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public void setFullLoad(FullLoad_Request fullLoad) {

		String clob = new String();
		ObjectOutputStream out;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(baos);
			out.writeObject(fullLoad);
			clob = Base64.getEncoder().encodeToString(baos.toByteArray());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.fullLoad = clob;
	}

}
*/