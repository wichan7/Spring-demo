package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardListMapping;
import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDetails;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Transactional
	public boolean registerMember(Member member) {
		if (memberRepository.findByUid(member.getUid()) != null) {
			
			return false;
		}
		member.setUpw(bcryptPasswordEncoder.encode(member.getUpw()));
		memberRepository.save(member);
		
		return true;
	}
	
	@Transactional
	public Member getCurrentMember() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Member member = ((MemberDetails)principal).getMember();
		
		return member;
	}
}


