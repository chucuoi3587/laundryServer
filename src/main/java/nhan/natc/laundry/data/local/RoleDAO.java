package nhan.natc.laundry.data.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "role")
public class RoleDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private int mId;
	
	@Column(name = "Description")
	private String mDescription;

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		this.mDescription = description;
	}

	public RoleDAO() {
		
	}
	
	public RoleDAO(int id, String description) {
		super();
		this.mId = id;
		this.mDescription = description;
	}
	
	
}
