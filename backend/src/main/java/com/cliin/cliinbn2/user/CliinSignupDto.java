package com.cliin.cliinbn2.user;

import lombok.Data;

@Data
public class CliinSignupDto {
    String nickname;
    String userId;
    String userPassword;
    String email;
}
