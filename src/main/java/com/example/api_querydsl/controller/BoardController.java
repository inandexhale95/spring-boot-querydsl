package com.example.api_querydsl.controller;

import com.example.api_querydsl.BoardService;
import com.example.api_querydsl.dto.BoardAndReplyCount;
import com.example.api_querydsl.dto.PageRequestDTO;
import com.example.api_querydsl.dto.PageResultDTO;
import com.example.api_querydsl.repository.BoardQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardQueryRepository boardQueryRepository;
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("{}", pageRequestDTO);

        PageResultDTO<BoardAndReplyCount> result = boardService.getList(pageRequestDTO);

        model.addAttribute("result", result);
    }
}
