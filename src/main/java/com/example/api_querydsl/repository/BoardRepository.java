package com.example.api_querydsl.repository;

import com.example.api_querydsl.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
