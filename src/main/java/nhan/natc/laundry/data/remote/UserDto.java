package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
	@JsonProperty("id")
	private Long mId;
	
	@JsonProperty("firstName")
	private String mFirstName;
	
	@JsonProperty("lastName")
	private String mLastName;
	
	@JsonProperty("email")
	private String mEmail;
	
	@JsonProperty("password")
	private String mPassword;

	public Long getId() {
		return mId;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String firstName) {
		this.mFirstName = firstName;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String lastName) {
		this.mLastName = lastName;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		this.mEmail = email;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		this.mPassword = password;
	}
	
}
