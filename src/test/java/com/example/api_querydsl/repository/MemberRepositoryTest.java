package com.example.api_querydsl.repository;

import com.example.api_querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void test_insertMemberRepository() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("USER_" + i + "@naver.com")
                    .password("123")
                    .nickname("USER_" + i)
                    .build();

            memberRepository.save(member);
        });
    }
}