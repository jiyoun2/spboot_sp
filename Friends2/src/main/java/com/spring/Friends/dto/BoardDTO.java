package com.spring.Friends.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@Data
public class BoardDTO {
    private Long id; // 글 번호

    @NotEmpty(message = "제목은 필수입니다.")
    private String title; // 글 제목
    @NotEmpty(message = "내용은 필수입니다.")
    private String content; // 글 내용

    private Integer hits; // 조회수
    private String writer; // 작성자
    private Timestamp regDate; // 작성일
}
