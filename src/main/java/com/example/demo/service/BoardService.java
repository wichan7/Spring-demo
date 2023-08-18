package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Board;
import com.example.demo.domain.BoardListMapping;
import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDetails;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Transactional
	public Board getBoardDetail(Long boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow();
		board.setViews(board.getViews() + 1);
		return board;
	}
	
	@Transactional
	public List<BoardListMapping> getBoardList() {
		
		return boardRepository.findAllBy();
	}
	
	@Transactional
	public Board insertBoard(Board board) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Member member = ((MemberDetails)principal).getMember();
		board.setWriter(member.getId());
		board.setNickname(member.getNickname());
		
		return boardRepository.save(board);
	}
}