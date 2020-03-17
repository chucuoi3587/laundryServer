package nhan.natc.laundry.data.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer")
public class CustomerDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long mId;
	
	@Column(name = "name")
	private String mName;
	
	@Column(name = "email")
	private String mEmail;
	
	@Column(name = "address")
	private String mAddress;
	
	@Column(name = "phone")
	private String mPhone;

	public CustomerDAO() {
		
	}
	
	public CustomerDAO(String name, String email, String address, String phone) {
		super();
		this.mName = name;
		this.mEmail = email;
		this.mAddress = address;
		this.mPhone = phone;
	}

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
