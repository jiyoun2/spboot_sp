package com.spring.Friends.controller;

import com.spring.Friends.dto.BoardDTO;
import com.spring.Friends.entity.Board;
import com.spring.Friends.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
@Controller // bean 등록
public class boardController {

    private final BoardService service;

    // 게시글 목록
    @GetMapping
    public String getBoardList(Model model) {
        List<Board> boardList = service.findAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }
    // 게시글 목록(페이지 처리)
    @GetMapping("/pages")
    public String getBoardPages(Model model, @PageableDefault(page = 1) Pageable pageable) {
        Page<Board> boardList = service.findAll(pageable);
        // 하단의 페이지 블럭
        int blockLimit = 10; // 1,2...10까지 보임
        // 시작 페이지 - 1, 2, 3 ...

        // 페이지 블럭의 시작 번호(게시글) - 1, 11, 21 ...
        // 예) 페이지 번호 - 13, 13/10-1.3 -> 2(올림) -1 * 10 + 1 => 11 (11~20 블럭에 존재)
        int startPage = ((int)Math.ceil((double)pageable.getPageNumber() / blockLimit)-1)*blockLimit+1;

        // 페이지의 행 끝 번호- 10, 20, 30 ... ;
        // int endPage = startPage + blockLimit -1
        // 존재하는 페이지까지만 나오게
//        int endPage = (startPage + blockLimit -1) > boardList.getTotalPages() ?
//                boardList.getTotalPages() : (startPage + blockLimit -1);
        int endPage = Math.min(startPage + blockLimit -1, boardList.getTotalPages());

        // 모델 보내기
        model.addAttribute("boardList", boardList); // 리스트 보내기
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/pages";
    }

    // 글쓰기 페이지
    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }

    // 글쓰기 처리
    @PostMapping("/write")
    public String write(@ModelAttribute BoardDTO dto) {
        log.info("BoardDTO: " + dto);
        service.save(dto);
        return "redirect:/boards";
    }

    // 글 상세보기
    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        try {
            // 조회수 증가
            service.updateHits(id);

            // 상세보기 호출
            Board board = service.findById(id);
            model.addAttribute("board", board);
            return "board/detail";
        } catch (Exception e) {
            return "error/errorPage";
        }

    }

    // 글 삭제
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/boards";
    }

    // 글 수정
    @GetMapping("/update/{id}")
    public String updateBoardForm(@PathVariable Long id, Model model) {
        Board board = service.findById(id);
        model.addAttribute("board", board);
        return "board/update";
    }

    // 글 수정 처리
    @PostMapping("/update")
    public String updateBoard(BoardDTO dto) {
        service.update(dto);
        return "redirect:/boards/" + dto.getId();
    }

}
