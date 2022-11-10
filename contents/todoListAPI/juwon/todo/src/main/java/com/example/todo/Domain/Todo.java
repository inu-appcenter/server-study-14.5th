package com.example.todo.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "내용을 입력해주세요")
	private String contents;

	@NotNull
	private Boolean isCompleted;

	@ManyToOne
	@JoinColumn
	private Member member;

	@Builder
	public Todo(Long id, String contents, Member member, Boolean isCompleted) {
		this.id = id;
		this.contents = contents;
		this.member = member;
		this.isCompleted = isCompleted;
	}

	public void updateIsCompleted(Boolean isCompleted) {
		this.isCompleted = !isCompleted;
	}

}
