package com.spring.Friends.repository;

import com.spring.Friends.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // @Query(value=sql구문)
    // @Modifying - 삽입, 수정 등의 변경이 있을 때 필수 적용
    @Modifying
    @Query(value = "update Board b set b.hits=b.hits+1 where b.id=:id")
    void updateHits(Long id);

    // 제목 검색어가 포함된 게시글 목록을 처리하여 조회
    // SELECT * FROM board WHERE TITLE LIKE ?
    Page<Board> findByTitleContaining(String keyword, Pageable pageable); // 검색어, 페이지 처리

    // 글 내용에 특정 검색어가 포함된
    Page<Board> findByContentContaining(String keyword, Pageable pageable);
}
