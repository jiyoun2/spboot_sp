package com.spring2;

import com.spring2.entity.Board;
import com.spring2.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
@SpringBootTest
public class QueryMethodTest {

    @Autowired // new 대신 사용; repository 객체를 생성해줌
    private BoardRepository repository;

//    @BeforeEach
//    public void dataPrepare() {
//        for (int i=1; i<=200; i++) {
//            Board board = new Board();
//            board.setTitle("test title " + i);
//            board.setWriter("tester");
//            board.setContent("test content " + i);
//
//            repository.save(board);
//        }
//    }
//
//    @Test
//    public void testFindByTitle() {
//        List<Board> boardList = repository.findByTitle("test title 4");
//
//        log.info("검색 결과");
//        for(Board board : boardList) {
//            log.info("->" + board.toString());
//        }
//    }

//    @Test
//    public void testFindByTitleContaining() {
//        List<Board> boardList = repository.findByTitleContaining("10");
//
//        log.info("검색 결과");
//        for(Board board : boardList) {
//            log.info("->" + board.toString());
//        }
//    }

//        @Test
//        public void testFindByTitleContainingOrderByIdDesc() {
//        List<Board> boardList = repository.findByTitleContainingOrderByIdDesc("10");
//
//        log.info("검색 결과");
//        for(Board board : boardList) {
//            log.info("->" + board.toString());
//        }
//    }
//
//        @Test
//        public void testFindByTitleContainingPage() {
//            // 0: 첫 페이지 번호(oageNumber), 10: 페이지당 데이터 수(pageSize)
////            Pageable paging = PageRequest.of(0,10);
//            Pageable paging = PageRequest.of(1,10, Sort.Direction.DESC, "id");
//
//
//
//            List<Board> boardList = repository.findByTitleContaining("title",paging);
//
//        log.info("검색 결과");
//        for(Board board : boardList) {
//            log.info("->" + board.toString());
//        }
//    }


@Test
public void testFindByTitleContainingPage() {
    // 0: 첫 페이지 번호(oageNumber), 10: 페이지당 데이터 수(pageSize)
    Pageable paging = PageRequest.of(1,10, Sort.Direction.DESC, "id");

    // 페이지 정보 객체 생성
    Page<Board> pageInfo = repository.findByTitleContaining("title",paging);

    log.info("Page Size: " + pageInfo.getSize()); // 10(페이지당 데이터 수)
    log.info("Total Pages: " + pageInfo.getTotalPages());// 40페이지
    log.info("Total Data: "+ pageInfo.getTotalElements()); // 400개
//    log.info("Contents: " + pageInfo.getContent()); // 저장된 데이터

    List<Board> boardList = pageInfo.getContent();

    log.info("검색결과");
    boardList.forEach(board -> log.info(" " + board)); // 화살표 함수=람다

}


}
