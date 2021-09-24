package pojo;

import java.io.Serializable;

public class Customer implements Serializable {
	private String name; 
	private String gender;
	private String bg;
	private String date;
	private String phone;
	private String address;
	private int units;
	private float rate;
	private float amount;
	
	public Customer() {
		name = new String();
		gender = new String();
		bg = new String();
		date = new String();
		phone = new String();
		address = new String();
	}
	
	public Customer(String name, String gender, String bg, String date, String phone, String address, int units,
			float rate, float amount) {
		super();
		this.name = name;
		this.gender = gender;
		this.bg = bg;
		this.date = date;
		this.phone = phone;
		this.address = address;
		this.units = units;
		this.rate = rate;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getBg() {
		return bg;
	}

	public String getDate() {
		return date;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public int getUnits() {
		return units;
	}

	public float getRate() {
		return rate;
	}

	public float getAmount() {
		return amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((bg == null) ? 0 : bg.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + Float.floatToIntBits(rate);
		result = prime * result + units;
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (bg == null) {
			if (other.bg != null)
				return false;
		} else if (!bg.equals(other.bg))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate))
			return false;
		if (units != other.units)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", gender=" + gender + ", bg=" + bg + ", date=" + date + ", phone=" + phone
				+ ", address=" + address + ", units=" + units + ", rate=" + rate + ", amount=" + amount + "]";
	}
}
