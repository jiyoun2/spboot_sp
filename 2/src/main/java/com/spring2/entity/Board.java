package com.spring2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor // 기본 생성자
//@Builder
@ToString
@Setter
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 순번 증가
    // 번호, 제목, 글쓴이, 내용, 작성일
    private Integer id;
    @Column(nullable = false)  // not null
    private String title;
    @Column(length = 20, nullable = false)
    private String writer;
    @Column(length = 4000, nullable = false)
    private String content;
    @CreationTimestamp // 작성일 자동 저장
    private Timestamp createDate;
}
