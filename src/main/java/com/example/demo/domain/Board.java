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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
	
	@Column(length=100, nullable=false)
	private String title;

	@Column(length=4000, nullable=false)
	private String content;

	private Long writer;
	
	@ColumnDefault("0")
	private Long views;
	
	@PrePersist
	public void prePersist() {
		this.views = this.views == null ? 0 : this.views;
		this.title = "".equals(this.title) ? null : this.title;
		this.content = "".equals(this.content) ? null : this.content;
	}
}