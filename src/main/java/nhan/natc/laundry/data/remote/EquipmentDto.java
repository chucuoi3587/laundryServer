package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EquipmentDto {
	
	@JsonProperty("id")
	private String mId;
	
	@JsonProperty("name")
	private String mName;
	
	@JsonProperty("description")
	private String mDescription;

	public String getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

	public String getDescription() {
		return mDescription;
	}
	
}
