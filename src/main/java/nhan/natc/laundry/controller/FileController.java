package nhan.natc.laundry.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import nhan.natc.laundry.data.local.FileDAO;
import nhan.natc.laundry.payload.DefaultResponse;
import nhan.natc.laundry.payload.UploadFileResponse;
import nhan.natc.laundry.service.DBFileService;

@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	DBFileService dbFileService;
	
	@PostMapping("/file/upload")
	public ResponseEntity<DefaultResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		FileDAO fileDao = dbFileService.storeFile(file);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/file/download/")
//                .path(fileDao.getId())
//                .toUriString();
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(new UploadFileResponse(fileDao.getFileName(), fileDao.getId(), fileDao.getFileType(), file.getSize())));			
	}
	
	@PostMapping("/file/uploadMulti")
	public List<ResponseEntity<DefaultResponse>> uploadMultiFile(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/file/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        FileDAO dbFile = dbFileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
}
