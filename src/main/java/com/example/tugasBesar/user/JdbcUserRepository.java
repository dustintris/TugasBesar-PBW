package com.example.tugasBesar.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(User user) throws Exception {
        String sql = "INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?)";
        String role = "admin";
        jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.getPassword(), role);
    }
    

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> results = jdbcTemplate.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> results = jdbcTemplate.query(sql, this::mapRowToUser, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
            resultSet.getInt("id"),
            resultSet.getString("email"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("password"),
            resultSet.getString("role")
    );

    }

}

