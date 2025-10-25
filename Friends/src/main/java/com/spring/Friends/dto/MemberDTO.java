package com.spring.Friends.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class MemberDTO {
    // 번호, 이메일, 비밀번호, 이름, 성별, 가입일
    private Long id;
    private String email;
    private String pwd;
    private String name;
    private String gender;
    private Timestamp joinDate;
}
