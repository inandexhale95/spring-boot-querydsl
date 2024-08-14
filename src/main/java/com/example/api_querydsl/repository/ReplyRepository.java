package com.example.api_querydsl.repository;

import com.example.api_querydsl.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
