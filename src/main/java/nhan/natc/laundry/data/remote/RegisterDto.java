package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDto {

	@JsonProperty("id")
	private Long mId;
	
	@JsonProperty("email")
	private String mEmail;
	
	@JsonProperty("password")
	private String mPassword;
	
	@JsonProperty("firstname")
	private String mFirstName;
	
	@JsonProperty("lastname")
	private String mLastName;
	
	@JsonProperty("role_id")
	private int mRoleId;
	
	@JsonProperty("avatar")
	private String mAvatar;

	public Long getId() {
		return mId;
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

	public int getRoleId() {
		return mRoleId;
	}

	public void setRoleId(int roleId) {
		this.mRoleId = roleId;
	}

	public String getAvatar() {
		return mAvatar;
	}

	public void setAvatar(String avatar) {
		this.mAvatar = avatar;
	}
		
}
