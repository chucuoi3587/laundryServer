package nhan.natc.laundry.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DefaultResponse<T> {

	@JsonProperty("data")
	private T mData;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("has_more_record")
    private Boolean hasMoreRecord;

	public T getData() {
		return mData;
	}

	public void setData(T data) {
		this.mData = data;
	}

	public DefaultResponse(T data) {
		super();
		this.mData = data;
	}

	public DefaultResponse(T data, Boolean hasMoreRecord) {
		super();
		this.mData = data;
		this.hasMoreRecord = hasMoreRecord;
	}
			
}
