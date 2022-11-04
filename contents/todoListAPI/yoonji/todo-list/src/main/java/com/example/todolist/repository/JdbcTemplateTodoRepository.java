package com.example.todolist.repository;

import com.example.todolist.domain.Member;
import com.example.todolist.domain.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateTodoRepository implements TodoRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Todo save(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("content", todo.getContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        todo.setId(key.longValue());
        return todo;
    }

    @Override
    public Optional<Todo> findById(Long id) {
        List<Todo> result = jdbcTemplate.query("select * from todo where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<Todo> memberRowMapper() {
        return (rs, rowNum) -> {
            Todo toDo = new Todo();
            toDo.setId(rs.getLong("id"));
            toDo.setContent(rs.getString("content"));
            toDo.setCompleted(rs.getBoolean("isCompleted"));
            toDo.setMember((Member) rs.getObject("member"));
            return toDo;
        };
    }

    @Override
    public List<Todo> findAll() {
        return jdbcTemplate.query("select * from todo", memberRowMapper());
    }
}
