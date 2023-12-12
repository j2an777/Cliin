package com.cliin.cliinbn2.user;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileStorageService {

    @Value("${file.upload.directory}")
    private String fileUploadDirectory;

    @Value("${file.upload.directory2}")
    private String fileUploadDirectory2;

    public String storeFile(MultipartFile file) throws IOException {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String filePath = Paths.get(fileUploadDirectory, fileName).toString();

            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            return fileName; // 파일명 또는 파일 경로를 반환
        } catch (IOException ex) {
            throw new IOException("파일 저장에 실패했습니다.", ex);
        }
    }

    public String storeFile2(MultipartFile file) throws IOException {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String filePath = Paths.get(fileUploadDirectory2, fileName).toString();

            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new IOException("파일 저장에 실패했습니다.", ex);
        }
    }
}

