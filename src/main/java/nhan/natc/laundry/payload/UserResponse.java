package nhan.natc.laundry.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import nhan.natc.laundry.data.local.FileDAO;
import nhan.natc.laundry.data.local.RoleDAO;
import nhan.natc.laundry.data.local.UserStatusDAO;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class UserResponse {
	@JsonProperty("id")
	private long mId;
	
	@JsonProperty("email")
	private String mEmail;
	
	@JsonProperty("firstname")
	private String mFirstName;
	
	@JsonProperty("lastname")
	private String mLastName;
	
	@JsonProperty("userStatus")
	private UserStatusDAO mUserStatus;
	
	@JsonProperty("role")
	private RoleDAO mRole;
	
	@JsonProperty("avatar")
	private String mAvatar;
	
	public UserResponse(long mId, String mEmail, String mFirstName, String mLastName, UserStatusDAO mUserStatus, RoleDAO role, FileDAO avatar) {
		super();
		this.mId = mId;
		this.mEmail = mEmail;
		this.mFirstName = mFirstName;
		this.mLastName = mLastName;
		this.mUserStatus = mUserStatus;
		this.mRole = role;
		if (avatar != null)
			this.mAvatar = avatar.getId();
	}	

}
