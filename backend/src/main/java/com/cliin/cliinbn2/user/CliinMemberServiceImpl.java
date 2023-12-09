package com.cliin.cliinbn2.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CliinMemberServiceImpl implements CliinMemberService {
    private final UserJpaRepository userJpaRepository;
    private final MissionJpaRepository missionJpaRepository;

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

    @Override
    public Mission upload(CliinMissionDto cliinMissionDto) {
        String fileName = StringUtils.cleanPath(cliinMissionDto.file.getOriginalFilename());

        try {
            Mission uploadList = Mission.builder()
                    .title(title)
                    .hashtag(hashtag)
                    .imageUrl("/path/to/your/upload/directory/" + fileName) // 경로는 적절히 변경해주세요
                    .contents(contents)
                    .build();

            // 파일 저장
            FileUploadUtil.saveFile("/path/to/your/upload/directory/", fileName, file); // 경로는 적절히 변경해주세요
            missionJpaRepository.save(uploadList);

            return uploadList;
        } catch (Exception e) {
            // 에러 처리
            e.printStackTrace();
            throw new YourCustomException("파일 업로드에 실패했습니다.");
        }
    }

}
