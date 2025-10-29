package com.spring.Friends.controller;

import com.spring.Friends.dto.MemberDTO;
import com.spring.Friends.entity.Member;
import com.spring.Friends.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("/members")
@Controller
public class memberController {

    // 서비스 인스턴스 생성
    private MemberService service;

    // 회원가입 페이지
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    // 회원 가입 처리
    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO memberDTO) {
        log.info("member: " + memberDTO);
        service.save(memberDTO); // 서비스에 있는 save()를 호출
        return "redirect:/";
    }

    // 회원 목록
    @GetMapping
    public String getMemberList(Model model) {
        List<Member> memberList = service.findAll();
        // 모델로 저장해서 보내기
        model.addAttribute("memberList", memberList);
        return "member/list";
    }

    // 회원 정보 1명 불러오기
    @GetMapping("/{id}") // id - 경로 변수
    public String getMember(@PathVariable Long id,
            Model model) {
        Member member = service.findById(id);
        model.addAttribute("member", member); // 회원 보내기
        return "member/info";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String pwd,
                        HttpSession session, RedirectAttributes ra) {
        try {
            // 로그인 체크 메서드 호출
            MemberDTO dto = service.login(email,pwd);
            // 로그인 성공 시 - 세션 발급
            session.setAttribute("loginEmail", dto.getEmail());
            session.setAttribute("loginName", dto.getName());
            return "redirect:/";
        } catch (Exception e){
            // 로그인 실패, RedirectAttributes는 redirect 상태
            ra.addFlashAttribute("error",e.getMessage());
            return "redirect:/members/login";
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 삭제
        session.invalidate();
        return "redirect:/";
    }
}
