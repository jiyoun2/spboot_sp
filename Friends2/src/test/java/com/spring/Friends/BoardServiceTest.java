package com.spring.Friends;

import com.spring.Friends.dto.BoardDTO;
import com.spring.Friends.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {
    @Autowired // new없이 객체 생성
    private BoardService service;

    @Test
    void testInsertData() {
        for(int i = 0; i <= 123; i++) {
            BoardDTO dto = new BoardDTO();
            dto.setTitle("Test Title: " + i);
            dto.setContent("Test Content: " + i);
            dto.setWriter("tester");

            service.save(dto);
        }
    }
}
