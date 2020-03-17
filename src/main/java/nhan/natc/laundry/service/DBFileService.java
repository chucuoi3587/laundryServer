package nhan.natc.laundry.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import nhan.natc.laundry.data.local.FileDAO;
import nhan.natc.laundry.data.repository.DBFileRepository;
import nhan.natc.laundry.exception.FileNotFoundException;
import nhan.natc.laundry.exception.FileStorageException;

@Service("dbFileService")
public class DBFileService {

	@Autowired
	DBFileRepository dbFileRepository;
	
	public FileDAO storeFile(MultipartFile file) {
		if (file == null)
			throw new FileStorageException("File upload is null");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            FileDAO dbFile = new FileDAO(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	
	public FileDAO getFile(String fileId) {
        return dbFileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
