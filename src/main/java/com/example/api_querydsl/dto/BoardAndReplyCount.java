package com.example.api_querydsl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class BoardAndReplyCount {
    private String email;
    private String nickname;

    private Long seq;
    private String title;
    private LocalDateTime createAt, updateAt;

    private Long replyCount;
}
