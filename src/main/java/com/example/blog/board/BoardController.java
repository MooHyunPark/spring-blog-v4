package com.example.blog.board;

import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RequiredArgsConstructor// final이 붙어있는 변수의 생성자를 만들어준다
@RestController
public class BoardController {

    private final BoardService boardService;

    // json을 리턴하는 경로는 api를 붙이는 경우가 많다.
    @GetMapping("/api")
    public Resp<?> list() {
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        return Resp.ok(boardList);
    }

    @PostMapping("/api/board")
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null);
    }

    @PutMapping("/api/board/{id}")
    public Resp<?> update(@Valid @RequestBody BoardRequest.UpdateDTO updateDTO, Errors errors, @PathVariable("id") Integer id) {

        boardService.게시글수정(id, updateDTO);
        return Resp.ok(null);
    }

    @DeleteMapping("/api/board/{id}")
    public Resp<?> delete(@PathVariable("id") Integer id) {
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }

    /**
     * 쿼리스트림(where절) : /board?title=바다
     * 패스변수(where절) : /board/1
     */
    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") Integer id) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글조회하기(id);
        return Resp.ok(boardDetail);
    }
}


