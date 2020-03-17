package nhan.natc.laundry.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadFileResponse {
//	@JsonProperty("filename")
	private String mFileName;
	
//	@JsonProperty("file_download_uri")
	private String mFileDownloadUri;
	
//	@JsonProperty("filetype")
	private String mFileType;
	
//	@JsonProperty("size")
    private long mSize;
	
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		super();
		this.mFileName = fileName;
		this.mFileDownloadUri = fileDownloadUri;
		this.mFileType = fileType;
		this.mSize = size;
	}
	
	public String getFileName() {
		return mFileName;
	}
	
	public String getFileDownloadUri() {
		return mFileDownloadUri;
	}
	
	public String getFileType() {
		return mFileType;
	}
	
	public long getSize() {
		return mSize;
	}
}
