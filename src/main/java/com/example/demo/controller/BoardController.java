package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/list")
	public ModelAndView listPage() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("boardList", boardService.getBoardList());
		mav.setViewName("/board/list");
		return mav;
	}
	
	@GetMapping("/board/{id}")
	public ModelAndView detailPage(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board", boardService.getBoardDetail(id));
		mav.setViewName("/board/detail");
		return mav;
	}
	
	@GetMapping("/board/register")
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/board/register");
		return mav;
	}
	
	@PostMapping("/board/register")
	public String register(@ModelAttribute Board board) {
		try {
			boardService.insertBoard(board);
		} catch(Exception e) {
			return "redirect:/error";
		}
		
		return "redirect:/board/list";
	}
	
}
