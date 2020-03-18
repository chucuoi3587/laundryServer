package nhan.natc.laundry.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import nhan.natc.laundry.data.local.CustomerDAO;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class CustomerResponse {
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
	
	public CustomerResponse(Long id, String name, String email, String address, String phone) {
		super();
		this.mId = id;
		this.mName = name;
		this.mEmail = email;
		this.mAddress = address;
		this.mPhone = phone;
	}

	public static final CustomerResponse fromEntity(CustomerDAO customerDAO) {
		return new CustomerResponse(customerDAO.getId(), customerDAO.getName(), customerDAO.getEmail(), customerDAO.getAddress(), customerDAO.getPhone());
	}
}
