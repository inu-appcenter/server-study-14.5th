package com.appcenter02.todo04.domain;

import com.appcenter02.todo04.common.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
public class Todo extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long todoId;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn
    private Usr usr;
}
