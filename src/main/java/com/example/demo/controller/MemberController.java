package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/member/login")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/member/login");
		return mav;
	}
	
	@GetMapping("/member/register")
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/member/register");
		return mav;
	}
	
	@PostMapping("/member/register")
	public ModelAndView register(@ModelAttribute Member member) {
		ModelAndView mav = new ModelAndView();
		memberService.insertMember(member);
		mav.setViewName("/member/login");
		
		return mav;
	}
}
