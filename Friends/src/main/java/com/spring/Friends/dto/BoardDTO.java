package com.spring.Friends.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
public class BoardDTO {
    private Long id; // 글 번호
    private String title; // 글 제목
    private String content; // 글 내용
    private Integer hits; // 조회수
    private String writer; // 작성자
    private Timestamp regDate; // 작성일
}
