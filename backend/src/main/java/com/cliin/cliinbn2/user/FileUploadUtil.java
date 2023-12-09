package com.cliin.cliinbn2.user;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            File file = new File(uploadPath + "/" + fileName);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("파일을 저장하는 중에 오류가 발생했습니다.");
        }
    }
}
