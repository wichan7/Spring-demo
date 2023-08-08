package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public Board getBoardDetail(Long boardId) {

		return boardRepository.findById(boardId).orElseThrow();
	}
	
	public List<Board> getBoardList() {
		
		return boardRepository.findAll();
	}
	
	public String insertBoard(Board board) {
		return "";
	}
}
