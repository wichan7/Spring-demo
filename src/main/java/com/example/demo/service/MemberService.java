package com.example.demo.service;

import java.util.List;

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
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Transactional
	public Member insertMember(Member member) {
		member.setUpw(bcryptPasswordEncoder.encode(member.getUpw()));
		return memberRepository.save(member);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUid(username);
		
		if (member != null) {
			return new MemberDetails(member);
		}
		
		return null;
	}
	
}


