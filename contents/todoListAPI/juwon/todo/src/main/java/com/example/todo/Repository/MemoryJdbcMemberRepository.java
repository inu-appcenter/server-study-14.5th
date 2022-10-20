package com.example.todo.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.activation.DataSource;

import com.example.todo.Domain.Member;

public class MemoryJdbcMemberRepository implements JdbcMemberRepository{
	
    private final DataSource dataSource;

    public MemoryJdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long idx = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++idx);
		store.put(member.getId(), member);
		return member;
		
	}
	
	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}
	
	@Override
	public Optional<Member> findByEmail(String email) {
		return store.values().stream().filter(member -> member.getEmail().equals(email)).findAny();
	}
	
	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
	}
	
	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
}
