package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/main");
		
		return mav;
	}
}
