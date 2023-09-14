package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final BoardService boardService;
	
	@GetMapping("")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(System.getProperty("spring.profiles.active"));
		mav.addObject("dailyTop", boardService.getBoardListDailyTop());
		mav.addObject("weeklyTop", boardService.getBoardListWeeklyTop());
		mav.addObject("monthlyTop", boardService.getBoardListMonthlyTop());
		mav.setViewName("main");
		
		return mav;
	}
	
	@GetMapping("error")
	public ModelAndView errorPage() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("error");
		
		return mav;
	}
}
