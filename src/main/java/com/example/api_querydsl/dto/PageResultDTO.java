package com.example.api_querydsl.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO> {
    List<DTO> dtoList;

    private int totalPageCount;

    private int currentPage;
    private int size;

    private int start, end;
    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDTO(Page<DTO> result) {
        this.dtoList = result.toList();
        totalPageCount = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        currentPage = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(currentPage / 10.0) * 10);

        start = tempEnd - 9;
        end = Math.min(tempEnd, totalPageCount);

        prev = start > 1;
        next = totalPageCount > tempEnd;

        pageList = IntStream.rangeClosed(start, end)
                .boxed()
                .toList();
    }
}
