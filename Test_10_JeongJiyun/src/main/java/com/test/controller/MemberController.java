package com.test.controller;

import com.test.dto.MemberDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@AllArgsConstructor
@Slf4j
@RequestMapping("/members")
@Controller
public class MemberController {

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupForm(@ModelAttribute MemberDTO memberDTO) {
        return "member/signup";

    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // 에러가 있으면 상품 등록 폼으로 이동
            System.out.println("bindingRfesult = " + bindingResult.getFieldError().toString());
            return "member/signup";
        } // 에러가 없으면 메인으로
        return "redirect:/";
    }



}
