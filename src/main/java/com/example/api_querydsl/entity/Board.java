package com.example.api_querydsl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"writer", "replies"})
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    // mappedBy 속성을 사용해야 한다.
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies;

    public Board(Long seq, String title, LocalDateTime createAt, LocalDateTime updateAt) {
        this.seq = seq;
        this.title = title;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
