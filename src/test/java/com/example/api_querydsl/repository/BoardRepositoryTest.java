package com.example.api_querydsl.repository;

import com.example.api_querydsl.dto.BoardAndReplyCount;
import com.example.api_querydsl.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardQueryRepository boardQueryRepository;

    @Test
    public void test_insertBoardRepository() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            memberRepository.findById("USER_" + i + "@naver.com")
                    .ifPresent(user -> {
                        Board board = Board.builder()
                                .title("TITLE..." + i)
                                .content("CONTENT..." + i)
                                .writer(user)
                                .build();

                        boardRepository.save(board);
                    });
        });
    }

    @Test
    public void test_getBoardAndReplies() {
        Board boardAndReplies = boardQueryRepository.getBoardAndReplies(1L);

        System.out.println(boardAndReplies);
        boardAndReplies.getReplies().forEach(System.out::println);
    }

    @Test
    public void test_getBoardAndReplyCountList() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<BoardAndReplyCount> result = boardQueryRepository.getBoardAndReplyCountList("tc", "", pageable);

        System.out.println(result.getTotalPages());
        System.out.println(result.getSize());

        result.getContent().forEach(System.out::println);
    }
}