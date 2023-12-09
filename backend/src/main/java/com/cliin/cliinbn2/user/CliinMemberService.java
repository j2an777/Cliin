package com.cliin.cliinbn2.user;

public interface CliinMemberService {
    User signup(CliinSignupDto cliinSignupDto);

    User login(CliinLoginDto cliinLoginDto);

    Mission upload(CliinMissionDto cliinMissionDto);
}
