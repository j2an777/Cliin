package com.cliin.cliinbn2.user;

import java.util.List;

public interface CliinMemberService {
    User signup(CliinSignupDto cliinSignupDto);

    User login(CliinLoginDto cliinLoginDto);

    Mission upload(CliinMissionDto cliinMissionDto, String userId);

    List<Mission> getAllMissions();

    Article articleUpload(CliinArticleDto cliinArticleDto, String userId);

    List<Article> getAllArticles();

    CliinProfileDto getProfile(String userId);
}
