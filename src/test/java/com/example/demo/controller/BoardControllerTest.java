package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.BoardService;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	BoardService boardService;
	
}
