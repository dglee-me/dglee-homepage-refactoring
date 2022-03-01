package com.dglee.apps.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
public class User {

    @Id
    @Column(name = "userUid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userUid;

    @Column(name = "emailId")
    @NonNull
    private String emailId;

    @Column(name = "pw")
    private String pw;

    @Column(name = "userName")
    @NonNull
    private String userName;

    @Column(name = "flagDelete")
    private int flagDelete = 0;  /* 0: 정상 1: 임시 2: 휴면 4: 탈퇴 */

    @Column(name = "registDate")
    private Long registDate = 0L;

    @Column(name = "lastLoginDate")
    private Long lastLoginDate = 0L;

    @Column(name = "deleteDate")
    private Long deleteDate = 0L;
}
