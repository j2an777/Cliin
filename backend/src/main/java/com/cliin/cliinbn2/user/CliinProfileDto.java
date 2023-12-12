package com.cliin.cliinbn2.user;

import lombok.Data;

@Data
public class CliinProfileDto {
    String userId;
    String nickname;
    Integer level;
    Integer point;
    Integer experience;
}
