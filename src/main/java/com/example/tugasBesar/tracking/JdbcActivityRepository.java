package com.example.tugasBesar.tracking;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcActivityRepository implements ActivityRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Activity activity) throws Exception {
        String sql = "INSERT INTO activities (title, deskripsi, date, duration, jarak) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            activity.getTitle(),
            activity.getDeskripsi(),
            activity.getDate(), // Assuming you have a method to get the date
            activity.getDuration(),
            activity.getJarak()
        );
    }

    @Override
    public Optional<Activity> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Activity> findByUserId(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Activity> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}