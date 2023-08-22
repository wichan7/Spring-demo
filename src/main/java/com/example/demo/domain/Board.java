package com.example.demo.domain;

import java.io.Serializable;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "board")
@Getter
@Setter
public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;
	
	@CreationTimestamp
	private java.sql.Timestamp createdAt;

	@NotBlank
	@Column(length=100, nullable=false)
	private String title;

	@NotBlank
	@Column(nullable=false)
	private String content;

	private Long writer;
	
	private String nickname;
	
	@ColumnDefault("0")
	@Column(nullable=false)
	private Long views;
	
	@PrePersist
	public void prePersist() {
		this.views = this.views == null ? 0 : this.views;
	}
}