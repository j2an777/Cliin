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
@Entity(name = "cln_mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "c_title", nullable = false)
    String title;

    @Column(name = "c_hashtag", nullable = false)
    String hashtag;

    @Column(name = "c_contents", nullable = false)
    String contents;

    @Column(name = "c_imgurl", nullable = false)
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "c_user_id", referencedColumnName = "c_id")
    private User user;
}
