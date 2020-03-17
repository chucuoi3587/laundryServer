package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDto {

	@JsonProperty("id")
	private Long mId;
	
	@JsonProperty("name")
	private String mName;
	
	@JsonProperty("email")
	private String mEmail;
	
	@JsonProperty("address")
	private String mAddress;
	
	@JsonProperty("phone")
	private String mPhone;

	public Long getId() {
		return mId;
	}
	
	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		this.mEmail = email;
	}

	public String getAddress() {
		return mAddress;
	}

	public void setAddress(String address) {
		this.mAddress = address;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String phone) {
		this.mPhone = phone;
	}
		
}
