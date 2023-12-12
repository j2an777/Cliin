package com.cliin.cliinbn2.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CliinMemberServiceImpl implements CliinMemberService {
    private final UserJpaRepository userJpaRepository;
    private final MissionJpaRepository missionJpaRepository;
    private final ArticleJpaRepository articleJpaRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Transactional()
    @Override
    public User signup(CliinSignupDto cliinSignupDto) {

        User cliinUser = User.builder()
                .nickname(cliinSignupDto.nickname)
                .userId(cliinSignupDto.userId)
                .password(cliinSignupDto.userPassword)
                .email(cliinSignupDto.email)
                .experience(0)
                .level(0)
                .point(0)
                .build();

        userJpaRepository.save(cliinUser);

        return cliinUser;
    }

    @Override
    public User login(CliinLoginDto cliinLoginDto) {
        Optional<User> userOptional = userJpaRepository.findByUserIdAndPassword(
                cliinLoginDto.userId,
                cliinLoginDto.userPassword
        );
        return userOptional.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Mission upload(CliinMissionDto cliinMissionDto, String userId) {
        try {
            String fileName = fileStorageService.storeFile(cliinMissionDto.getMultipartFile());

            String imageUrl = "/missionImg/" + fileName;

            Mission uploadList = Mission.builder()
                    .title(cliinMissionDto.getTitle())
                    .hashtag(cliinMissionDto.getHashtag())
                    .imageUrl(imageUrl)
                    .contents(cliinMissionDto.getContents())
                    .build();

            missionJpaRepository.save(uploadList);

            User user = userJpaRepository.findByUserId(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            user.setPoint(user.getPoint() + 50);
            userJpaRepository.save(user);

            return uploadList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MultipartException("파일 업로드에 실패했습니다.");
        }
    }

    public List<Mission> getAllMissions() {
        return missionJpaRepository.findAll();
    }

    public Article articleUpload(CliinArticleDto cliinArticleDto, String userId) {
        try {
            String fileName = fileStorageService.storeFile2(cliinArticleDto.getMultipartFile());

            String imageUrl = "/articleImg/" + fileName;

            Article uploadList = Article.builder()
                    .title(cliinArticleDto.getTitle())
                    .subtitle(cliinArticleDto.getSubtitle())
                    .hashtag(cliinArticleDto.getHashtag())
                    .imageUrl(imageUrl)
                    .content(cliinArticleDto.getContent())
                    .build();

            articleJpaRepository.save(uploadList);

            User user = userJpaRepository.findByUserId(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            int newExperience = user.getExperience() + 20;

            if (newExperience >= 100) {
                user.setExperience(0);
                user.setLevel(user.getLevel() + 1);
            } else {
                user.setExperience(newExperience);
            }

            userJpaRepository.save(user);

            return uploadList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MultipartException("파일 업로드에 실패했습니다.");
        }
    }

    public List<Article> getAllArticles() {
        return articleJpaRepository.findAll();
    }

    public CliinProfileDto getProfile(String userId) {
        User user = userJpaRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        CliinProfileDto profileDto = new CliinProfileDto();
        profileDto.setUserId(user.getUserId());
        profileDto.setNickname(user.getNickname());
        profileDto.setLevel(user.getLevel());
        profileDto.setPoint(user.getPoint());
        profileDto.setExperience(user.getExperience());

        return profileDto;
    }
}
