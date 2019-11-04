package pw.backend.lab.backlab.user.service;

import pw.backend.lab.backlab.user.exception.FileStorageException;
import pw.backend.lab.backlab.user.exception.MyUserNotFoundException;
import pw.backend.lab.backlab.user.User;
import pw.backend.lab.backlab.user.DBFileRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public User storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            User dbFile = new User(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public User getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyUserNotFoundException("File not found with id " + fileId));
    }
}