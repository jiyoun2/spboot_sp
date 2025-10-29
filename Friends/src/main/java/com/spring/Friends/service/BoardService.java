package com.spring.Friends.service;

import com.spring.Friends.dto.BoardDTO;
import com.spring.Friends.entity.Board;
import com.spring.Friends.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // 생성자 주입 방식(final)
@Service
public class BoardService {

    // @Required... final 상수 붙임
    private final BoardRepository repository;

    // 글쓰기
    public void save(BoardDTO dto) {
        // dto를 entity로 변환 메서드 호출
        Board board = Board.toSaveEntity(dto);
        repository.save(board);
    }

    // 글 목록
    public List<Board> findAll() {
        return repository.findAll();
    }

    // 글 상세보기
    public Board findById(Long id) {
        // id에 해당하는 게시글이 없는 경우 에러 구현
        Board board = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        return board;
    }

    // 조회수 증가 메소드
    @Transactional // 조회수, 상세보기 2개의 기능을 구현
    public void updateHits(Long id) {
        repository.updateHits(id);
    }

    public void delete(Long id) {
        // 제공된 deleteById() 사용
        repository.deleteById(id);
    }

    public void update(BoardDTO dto) {
        // id에 해당하는 게시글 가져오기
        Board board = repository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        // 제목, 내용 수정
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        repository.save(board); // 수정 내용 저장
    }

    // 글 목록(페이지 처리)
    public Page<Board> findAll(Pageable pageable) {
        // http://localhost:8080/boards/pages?page=1
        int page = pageable.getPageNumber()-1;
        int pageSize = 10;
        pageable = PageRequest.of(page,pageSize, Sort.Direction.DESC,"id"); // 내림차순
        Page<Board> boardList = repository.findAll(pageable);
        return boardList;
    }
}
