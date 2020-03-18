package nhan.natc.laundry.data.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public abstract class AbstractFilterRequest {
	//Paging =====================================
    @JsonProperty("fetch_page")
    private int fetchPage;

    @JsonProperty("fetch_limit")
    private int fetchLimit;

    @JsonProperty("total_record")
    private Long totalCount;

    @JsonProperty("has_more_record")
    private boolean hasMoreRecord;
    //Paging =====================================

	public int getFetchPage() {
		return fetchPage;
	}

	public void setFetchPage(int fetchPage) {
		this.fetchPage = fetchPage;
	}

	public int getFetchLimit() {
		return fetchLimit;
	}

	public void setFetchLimit(int fetchLimit) {
		this.fetchLimit = fetchLimit;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isHasMoreRecord() {
		return hasMoreRecord;
	}

	public void setHasMoreRecord(boolean hasMoreRecord) {
		this.hasMoreRecord = hasMoreRecord;
	}
    
}
