package nhan.natc.laundry.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class TokenResponse {

	@JsonProperty("access_token")
	private String mToken;
	
	public TokenResponse(String token) {
		this.mToken = token;
	}
}
