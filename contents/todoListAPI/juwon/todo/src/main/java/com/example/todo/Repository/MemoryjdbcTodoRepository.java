package com.example.todo.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.activation.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.example.todo.Domain.Member;
import com.example.todo.Domain.ToDo;

public class MemoryjdbcTodoRepository implements JdbcTodoRepository{
	
    private final DataSource dataSource;

    public MemoryjdbcTodoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private static Map<Long, ToDo> store = new HashMap<>();
	private static long idx = 0L;
	
	@Override
	public ToDo save(ToDo todo) {
		todo.setId(++idx);
		store.put(todo.getId(), todo);
		return todo;
	};

	@Override
	public Optional<ToDo> findById(Long id) {
		return store.values().stream().filter(todo -> todo.getId().equals(id)).findAny();
	};
	

	@Override
	public List<ToDo> findAll() {
		return new ArrayList<>(store.values());
	};
	
}
