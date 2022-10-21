package com.appcenter02.todo04.domain;

import com.appcenter02.todo04.common.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Usr extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    @Column

    private int age;

    @Column(columnDefinition="varchar(20)")
    private String job;

    @Column(columnDefinition="varchar(20)", nullable = false)
    private String username;

    @OneToMany(mappedBy = "usr")
    private List<Todo> todoList = new ArrayList<Todo>();
}
