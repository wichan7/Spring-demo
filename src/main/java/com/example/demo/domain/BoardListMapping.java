package com.example.demo.domain;

public interface BoardListMapping {
	
	Long getBoardId();
	
	java.sql.Timestamp getCreatedAt();
	
	String getTitle();
	
	Long getWriter();
	
	Long getViews();
	
}