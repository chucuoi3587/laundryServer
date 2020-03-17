package nhan.natc.laundry.data.local;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class UserDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long mId;
	
	@Column(name = "firstname")
	private String mFirstName;
	
	@Column(name = "lastname")
	private String mLastName;
	
	@Column(name = "email")
	private String mEmail;
	
	@Column(name = "password")
	private String mPassword;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private UserStatusDAO mUserStatus;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private RoleDAO mRole;
	
	@OneToOne
	@JoinColumn(name = "avatar")
	private FileDAO mAvatar;
	
	public UserDAO() {
		
	}

	public UserDAO(String firstName, String lastName, String email, String password, UserStatusDAO userStatus, RoleDAO role) {
		super();
		this.mFirstName = firstName;
		this.mLastName = lastName;
		this.mEmail = email;
		this.mPassword = password;
		this.mUserStatus = userStatus;
		this.mRole = role;
	}



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
	
	public UserStatusDAO getUserStatus() {
		return mUserStatus;
	}

	public void setUserStatus(UserStatusDAO userStatus) {
		this.mUserStatus = userStatus;
	}

	public RoleDAO getRole() {
		return mRole;
	}
	
	public void setRole(RoleDAO role) {
		this.mRole = role;
	}	
	
	public FileDAO getAvatar() {
		return mAvatar;
	}

	public void setAvatar(FileDAO avatar) {
		this.mAvatar = avatar;
	}

	public UserDAO(String firstName, String lastName, String email, String password) {
		super();
		this.mFirstName = firstName;
		this.mLastName = lastName;
		this.mEmail = email;
		this.mPassword = password;
	}
	
}
