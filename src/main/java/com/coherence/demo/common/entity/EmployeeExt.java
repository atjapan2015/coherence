package com.coherence.demo.common.entity;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

public class EmployeeExt implements PortableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1451283638803399635L;

	private BigDecimal id;

	private String firstname;

	private String lastname;

	private String email;

	private String phone;

	private String birthdate;

	private String title;

	private String department;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public EmployeeExt() {

	}

	public EmployeeExt(BigDecimal id, String firstname, String lastname, String email, String phone, String birthdate,
			String title, String department) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.birthdate = birthdate;
		this.title = title;
		this.department = department;

	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {

		setId(arg0.readBigDecimal(0));
		setFirstname(arg0.readString(1));
		setLastname(arg0.readString(2));
		setEmail(arg0.readString(3));
		setPhone(arg0.readString(4));
		setBirthdate(arg0.readString(5));
		setTitle(arg0.readString(6));
		setDepartment(arg0.readString(7));
	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {

		arg0.writeBigDecimal(0, getId());
		arg0.writeString(1, getFirstname());
		arg0.writeString(2, getLastname());
		arg0.writeString(3, getEmail());
		arg0.writeString(4, getPhone());
		arg0.writeString(5, getBirthdate());
		arg0.writeString(6, getTitle());
		arg0.writeString(7, getDepartment());
	}

	@Override
	public boolean equals(Object oThat) {

		if (this == oThat) {
			return true;
		}
		if (oThat == null) {
			return false;
		}
		EmployeeExt that = (EmployeeExt) oThat;
		return Base.equals(getId(), that.getId()) && Base.equals(getFirstname(), that.getFirstname())
				&& Base.equals(getLastname(), that.getLastname());
	}

	@Override
	public int hashCode() {
		return HashHelper.hash(getId(), HashHelper.hash(getFirstname(), HashHelper.hash(getLastname(), 0)));
	}

	@Override
	public String toString() {
		return getId() + "\n" + getFirstname() + "\n" + getLastname();
	}

}
