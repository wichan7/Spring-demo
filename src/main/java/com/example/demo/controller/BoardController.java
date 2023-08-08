package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/{id}")
	public ModelAndView boardDetail(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board", boardService.getBoardDetail(id));
		mav.setViewName("/board/detail");
		return mav;
	}
	
	@GetMapping("/board/list")
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("boards", boardService.getBoardList());
		mav.setViewName("/board/list");
		return mav;
	}
	
}
