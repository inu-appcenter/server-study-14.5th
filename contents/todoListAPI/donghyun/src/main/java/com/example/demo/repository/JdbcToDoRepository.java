package com.example.demo.repository;

import com.example.demo.domain.ToDo;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcToDoRepository implements ToDoRepository {

    private final DataSource dataSource;

    public JdbcToDoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ToDo save(ToDo toDo) {
        String sql = "insert into toDo(content) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, toDo.getContent());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                toDo.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return toDo;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public Optional<ToDo> findById(Long id) {
        String sql = "select * from toDo where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                ToDo toDo = new ToDo();
                toDo.setId(rs.getLong("id"));
                toDo.setContent(rs.getString("content"));
                return Optional.of(toDo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    @Override
    public List<ToDo> findAll() {
        String sql = "select * from toDo";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<ToDo> toDos = new ArrayList<>();
            while(rs.next()) {
                ToDo toDo = new ToDo();
                toDo.setId(rs.getLong("id"));
                toDo.setContent(rs.getString("content"));
                toDos.add(toDo);
            }
            return toDos;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
