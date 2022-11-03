package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn
    private Member member;

    @Builder
    public ToDo(Long id, String content, boolean isCompleted, Member member) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        this.member = member;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

