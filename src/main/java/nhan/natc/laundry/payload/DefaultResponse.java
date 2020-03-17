package nhan.natc.laundry.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultResponse {

	@JsonProperty("data")
	private Object mData;

	public Object getData() {
		return mData;
	}

	public void setData(Object data) {
		this.mData = data;
	}

	public DefaultResponse(Object data) {
		super();
		this.mData = data;
	}
			
}
