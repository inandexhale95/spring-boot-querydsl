package com.example.api_querydsl.repository;

import com.example.api_querydsl.dto.BoardAndReplyCount;
import com.example.api_querydsl.entity.Board;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.api_querydsl.entity.QBoard.board;
import static com.example.api_querydsl.entity.QMember.member;
import static com.example.api_querydsl.entity.QReply.reply;

@Log4j2
@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<BoardAndReplyCount> getBoardAndReplyCountList(String type, String keyword, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (type != null && keyword != null) {
            for (String t : type.split("")) {
                switch (t) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
        }

        List<BoardAndReplyCount> boardAndReplyCountList = jpaQueryFactory.select(Projections.constructor(
                        BoardAndReplyCount.class,
                        member.email,
                        member.nickname,
                        board.seq,
                        board.title,
                        board.createAt,
                        board.updateAt,
                        JPAExpressions.select(reply.count())
                                .from(reply)
                                .where(reply.board.eq(board))
                ))
                .from(board)
                .where(booleanBuilder)
                .leftJoin(board.writer, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(board.count())
                .from(board)
                .fetchFirst();

        return new PageImpl<>(boardAndReplyCountList, pageable, total);
    }

    public Board getBoardAndReplies(Long seq) {
        return jpaQueryFactory.selectFrom(board)
                .leftJoin(board.replies, reply)
                .fetchJoin()
                .where(board.seq.eq(seq))
                .fetchOne();
    }
}
