package com.example.todo.Domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contents;

	private Boolean isCompleted;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Member member;

	@Builder
	public Todo(Long id, String contents, Member member, Boolean isCompleted) {
		this.id = id;
		this.contents = contents;
		this.member = member;
		this.isCompleted = isCompleted;
	}

	public void update(String contents, Boolean isCompleted) {
		this.contents = contents;
		this.isCompleted = isCompleted;
	}

}
