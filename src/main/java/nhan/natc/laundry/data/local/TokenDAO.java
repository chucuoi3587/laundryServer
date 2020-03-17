package nhan.natc.laundry.data.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "token")
public class TokenDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Long mId;
	
	@Column(name = "accesstoken")
	private String mToken;
	
	@Column(name = "expire_time")
	private Long mExpiredTime;
	
//	@Column(name = "userid")
//	private Long mUserId;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private UserDAO mUser;

	public Long getId() {
		return mId;
	}

	public void setId(Long id) {
		this.mId = id;
	}

	public String getToken() {
		return mToken;
	}

	public void setToken(String token) {
		this.mToken = token;
	}
	
//	public Long getUserId() {
//		return mUserId;
//	}

	public void setUser(UserDAO user) {
		this.mUser = user;
	}
	
	public UserDAO getUser() {
		return mUser;
	}
	
	public TokenDAO() {
		
	}
	
	public TokenDAO(String token, UserDAO user, Long expiredTime) {
		super();
		this.mToken = token;
		this.mUser = user;
//		this.mUserId = userId;
		this.mExpiredTime = expiredTime;
	}
	
}
