package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;

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
		if (memberService.registerMember(member)) {
			mav.setViewName("/member/login");
		} else {
			mav.setViewName("/member/register");
		}
		
		return mav;
	}
	
	@GetMapping("/member/mypage")
	public ModelAndView myPage() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("member", memberService.getCurrentMember());
		mav.setViewName("/member/mypage");
		
		return mav;
	}
}
