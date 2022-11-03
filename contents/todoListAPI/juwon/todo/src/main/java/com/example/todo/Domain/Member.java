package com.example.todo.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Length(max = 20)
	@Column(columnDefinition = "varchar(20) default 'DefaultUser'")
	private String name;

	@Positive
	@Column(columnDefinition = "default 1")
	private int age;

	@Column(columnDefinition = "varchar(30) default 'example@example.com'")
	private String email;
	@OneToMany(mappedBy = "member")
	private List<ToDo> toDoList = new ArrayList<ToDo>();

	@Builder
	public Member(Long id, String name, int age, String email, List<ToDo> toDoList) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.toDoList = toDoList;
	}

	public void update(String name, int age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public void addToDo(ToDo todo) {
		this.toDoList.add(todo);
	}
}
