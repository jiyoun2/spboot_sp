package com.spring.Friends.entity;

import com.spring.Friends.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data // lombok의 getter, setter 등등 모두 적용됨
@Table(name = "t_board")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length=4000, nullable = false)
    private String content;

    @Column(columnDefinition = "Integer default 0")
    private Integer hits;

    @Column(length=30, nullable = false)
    private String writer;

    @CreationTimestamp
    private Timestamp regDate;

    // dto를 entity로 변환 메서드
    public static Board toSaveEntity(BoardDTO dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setHits(0);
        board.setWriter(dto.getWriter());

        return board;
    }
}
