package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryDto {

	@JsonProperty("id")
	private String mId;
	
	@JsonProperty("name")
	private String mName;
	
	public String getId() {
		return mId;
	}
	
	public String getName() {
		return mName;
	}
}
