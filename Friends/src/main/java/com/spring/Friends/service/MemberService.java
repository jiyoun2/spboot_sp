package com.spring.Friends.service;

import com.spring.Friends.dto.MemberDTO;
import com.spring.Friends.entity.Member;
import com.spring.Friends.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor // 매개변수를 모두 가진 생성자(생성자 주입)
@Service // 스프링에 bean으로 객체 등록(new 안해줘도)
public class MemberService {

    // 객체(인스턴스) 생성 방식 - 생성자 주입 방식
    private MemberRepository repository;

    // 회원 가입(추가)
    public void save(MemberDTO dto) {

        // DTO를 entity로 변환하는 메서드를 호출
        Member member = Member.toSaveEntity(dto);
        repository.save(member);
    }

    // 회원 목록
    public List<Member> findAll() {
        return repository.findAll();
    }

    // 회원 정보
    public Member findById(Long id) {
        Member member =  repository.findById(id).get();
        return member;
    }

    // 로그인 처리
    public MemberDTO login(String email, String pwd) {
        Member member = repository.findByEmail(email).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        // 비밀번호 체크
        if(!member.getPwd().equals(pwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        // entity save as dto
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setEmail(member.getEmail());
        dto.setName(member.getName());
        dto.setGender(member.getGender());

        return dto;
    }

}
