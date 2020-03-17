package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginDto {

	@JsonProperty("email")
	private String mEmail;
	
	@JsonProperty("password")
	private String mPassword;

	public String getEmail() {
		return mEmail;
	}

	public String getPassword() {
		return mPassword;
	}

}
