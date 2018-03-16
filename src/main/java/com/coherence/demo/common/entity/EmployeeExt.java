package com.coherence.demo.common.entity;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

public class EmployeeExt implements PortableObject {

	private Long id;

	private String firstname;

	private String lastname;

	public EmployeeExt() {

	}

	public EmployeeExt(Long id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Override
	public void readExternal(PofReader arg0) throws IOException {

		setId(arg0.readLong(0));

		setFirstname(arg0.readString(1));

		setLastname(arg0.readString(2));

	}

	@Override
	public void writeExternal(PofWriter arg0) throws IOException {

		arg0.writeLong(0, getId());

		arg0.writeString(1, getFirstname());

		arg0.writeString(2, getLastname());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
