package com.example.api_querydsl;

import com.example.api_querydsl.dto.BoardAndReplyCount;
import com.example.api_querydsl.dto.PageRequestDTO;
import com.example.api_querydsl.dto.PageResultDTO;
import com.example.api_querydsl.repository.BoardQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardQueryRepository boardQueryRepository;

    public PageResultDTO<BoardAndReplyCount> getList(PageRequestDTO pageRequestDTO) {

        Page<BoardAndReplyCount> boardAndReplyCountList = boardQueryRepository.getBoardAndReplyCountList(
                pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable(Sort.by("seq"))
        );

        return new PageResultDTO<>(boardAndReplyCountList);
    }
}
