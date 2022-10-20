package com.example.todo.Domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private int age;
	@Column
	private LocalDateTime createdAt;
	@Column
	private LocalDateTime updatedAt;
	@Column
	private String email;
	@OneToMany(mappedBy = "member")
	private List<ToDo> toDoList = new ArrayList<ToDo>();
}
