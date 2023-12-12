package com.cliin.cliinbn2.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Data
public class CliinMissionDto {
    Long id;
    private String title;
    private String hashtag;
    private String contents;
    private MultipartFile multipartFile;

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
