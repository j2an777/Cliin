package com.cliin.cliinbn2.user;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Data
public class CliinArticleDto {
    Long id;
    private String title;
    private String subtitle;
    private String hashtag;
    private String content;
    private MultipartFile multipartFile;

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
