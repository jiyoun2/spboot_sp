package com.test.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MemberDTO {
    // 번호, 이메일, 비밀번호, 이름, 가입일
    private Long id;

    @NotEmpty(message = "올바른 형식의 이메일 주소여야 합니다")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 항목입니다")
    private String pwd;
    @NotEmpty(message = "이름은 필수 항목입니다")
    private String name;

    private Timestamp joinDate;
}
