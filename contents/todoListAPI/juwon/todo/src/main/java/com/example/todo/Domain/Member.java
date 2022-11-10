package com.example.todo.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "이름을 적어주세요")
	private String name;

	@NotNull(message = "나이를 적어주세요")
	private int age;

	@NotNull(message = "이메일을 적어주세요")
	private String email;

	@OneToMany(mappedBy = "member")
	private List<Todo> toDoList = new ArrayList<Todo>();

	@Builder
	public Member(String name, int age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public void update(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
