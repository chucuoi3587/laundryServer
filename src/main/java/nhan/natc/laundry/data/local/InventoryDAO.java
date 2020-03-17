package nhan.natc.laundry.data.local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "inventory")
public class InventoryDAO {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
    private String mId;

	@Column(name = "name")
    private String mName;

	public InventoryDAO() {
		
	}
	
	public InventoryDAO(String name) {
		super();
		this.mName = name;
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
		
}
