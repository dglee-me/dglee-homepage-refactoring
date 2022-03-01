package com.dglee.apps.dto;

import com.dglee.apps.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Data
    public static class UserAdd implements DTO {

        @NotBlank
        String emailId;

        @NotBlank
        String pw;

        @NotBlank
        String userName;

        Long registDate = 0L;
    }

    private int userUid;

    private String emailId;

    private String pw;

    private String userName;

    private int flagDelete = 0;  /* 0: 정상 1: 임시 2: 휴면 4: 탈퇴 */

    private Long registDate = 0L;

    private Long lastLoginDate = 0L;

    private Long deleteDate = 0L;

    public User toEntity() {
        return new User(userUid, emailId, pw, userName, flagDelete, registDate, lastLoginDate, deleteDate);
    }
}
