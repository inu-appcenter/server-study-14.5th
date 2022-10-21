package com.example.todo.Domain;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ToDo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String contents;
	@Column
	private LocalDateTime date;
	@Column
	private boolean finished;
	@Column
	private LocalDateTime createdAt;
	@Column
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn
	private Member member;
}
