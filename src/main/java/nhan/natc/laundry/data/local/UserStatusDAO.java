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
@Table(name = "user_status")
public class UserStatusDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private Integer mId;
	
	@Column(name = "status_name")
	private String mStatusName;

	public UserStatusDAO() {
		
	}
	
	public Integer getId() {
		return mId;
	}

	public void setId(Integer id) {
		this.mId = id;
	}

	public String getStatusName() {
		return mStatusName;
	}

	public void setStatusName(String statusName) {
		this.mStatusName = statusName;
	}
	
}
