package com.example.todo.Domain;

import javax.persistence.*;

import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Length(max = 100)
	@Column(columnDefinition = "varchar(100) default 'blankContents'")
	private String contents;

	@Column(columnDefinition = "default false")
	private Boolean isCompleted;

	@ManyToOne
	@JoinColumn
	private Member member;

	@Builder
	public ToDo(Long id, String contents, Member member, Boolean isCompleted) {
		this.id = id;
		this.contents = contents;
		this.member = member;
		this.isCompleted = isCompleted;
	}

	public void update(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
