package com.example.demo.domain;

import java.io.Serializable;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@CreationTimestamp
	private java.sql.Timestamp createdAt;
	
	@Column(nullable = false, unique = true, length = 50)
	private String uid;
	
	@Column(nullable = false, length = 200)
	private String upw;
	
	@Column(nullable = false, length = 20)
	private String nickname;

	
}