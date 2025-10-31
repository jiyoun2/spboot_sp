package com.jwbook2.controller;

import com.jwbook2.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/valid01")
@Controller
public class ProductController {

    @GetMapping
    public String showForm(@ModelAttribute ProductDTO product) {

        return "validation/viewPage01";
    }

    @PostMapping
    public String addProduct(@Valid @ModelAttribute ProductDTO product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // 에러가 있으면 상품 등록 폼으로 이동
            System.out.println("bindingRfesult = " + bindingResult.getFieldError().toString());
            return "validation/viewPage01";
        } // 에러가 없으면 메인으로
        return "redirect:/";
    }
}
