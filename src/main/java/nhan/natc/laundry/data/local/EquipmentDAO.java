package nhan.natc.laundry.data.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "equipment")
public class EquipmentDAO {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
    private String mId;

	@Column(name = "name")
    private String mName;
	
	@Column(name = "description")
	private String mDescription;

	public EquipmentDAO() {
		
	}
	
	public EquipmentDAO(String name, String description) {
		this.mName = name;
		this.mDescription = description;
	}
	
	public String getId() {
		return mId;
	}

	public void setId(String id) {
		this.mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		this.mDescription = description;
	}
	
	
}
