package com.spring2;

import com.spring2.entity.Board;
import com.spring2.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired // 자동주입
    private BoardRepository repo;

    // 게시글 추가(등록)
//    @Test
//    public void insertBoard() {
//        Board board = new Board();
//
//        board.setTitle("가입 인사");
//        board.setWriter("박신입");
//        board.setContent("요로시쿠");
//
//        repo.save(Board); // board 객체 추가

//        Board board = Board.builder()
//            .title("안녕하세요")
//                .writer("이신입")
//                .content("반갑습니다")
//                .build();
//        repo.save(board);
//    }

    // 게시글 목록
//    @Test
//    public void getBoardList() {
//        List<Board> boardList = repo.findAll();
//
//        for (Board board : boardList) {
////            System.out.println(board.toString());
//            log.info("--->" + board.toString());
//        }
//    }
////    // 게시글 수정
//    @Test
//    public void updateBoard() {
//        log.info("=== 2번 게시글 조회 ===");
//        Board board = repo.findById(2).get();
//
//        log.info("2번 게시글 제목 수정");
//        board.setWriter("박신입");
//
//        // 수정 후 저장
//        repo.save(board);
//    }

    // 게시글 상세 보기(1건)
//    @Test
//    public void getBoard() {
//        log.info("=== 2번 게시글 조회 ===");
//        Optional<Board> board = repo.findById(2);
//        log.info("결과: " + board.toString());
//    }


//    // 게시글 삭제
//    @Test
//    public void deleteBoard() {
//        log.info("1번 게시글 삭제 ======");
//        repo.deleteById(1);
//    }

    // 게시글 목록
    @Test
    public void getBoardList() {
        List<Board> boardList = repo.findAll();

        for (Board board : boardList) {
//           System.out.println(board.toString());
            log.info("--->" + board.toString());
        }
    }
}
