package com.example.api_querydsl.repository;

import com.example.api_querydsl.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test_insertReply() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            boardRepository.findById((long) i)
                    .ifPresent(board -> {
                        int replyCount = (int) (Math.random() * 10) + 1;

                        IntStream.rangeClosed(1, replyCount).forEach(c -> {
                            Reply reply = Reply.builder()
                                    .content("CONTENT..." + i)
                                    .board(board)
                                    .build();

                            replyRepository.save(reply);
                        });

                    });
        });
    }
}