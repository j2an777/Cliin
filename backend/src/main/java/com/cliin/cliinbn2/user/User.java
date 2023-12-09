package com.cliin.cliinbn2.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "cln_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "c_no")
    String no;

    @Column(name = "c_nickname", nullable = false)
    String nickname;

    @Column(name = "c_id", nullable = false, unique = true)
    String userId;

    @Column(name = "c_password", nullable = false)
    String password;

    @Column(name = "c_email", nullable = false)
    String email;

    @Column(name = "c_experience", nullable = false)
    Integer experience;

    @Column(name = "c_level", nullable = false)
    Integer level;
}
