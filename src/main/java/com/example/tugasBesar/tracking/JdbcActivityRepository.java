package com.example.tugasBesar.tracking;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "INSERT INTO activities (title, deskripsi, date, duration, jarak, image) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            activity.getTitle(),
            activity.getDeskripsi(),
            activity.getDuration(),
            activity.getJarak(),
            activity.getImage()
        );
    }
    
    public Optional<Activity> findById(int id) {
        String sql = "SELECT * FROM activities WHERE id = ?";
        List<Activity> results = jdbcTemplate.query(sql, this::mapRowToActivity, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
z
    public Optional<Activity> findByUserId(int userId) {
        String sql = "SELECT * FROM activities WHERE user_id = ?";
        List<Activity> results = jdbcTemplate.query(sql, this::mapRowToActivity, userId);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public void deleteById(int id) throws Exception{
        String sql = "DELETE FROM activities WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    private Activity mapRowToActivity(ResultSet resultSet, int rowNum) throws SQLException {
        return new Activity(
            resultSet.getInt("id"),
            resultSet.getInt("userid"),
            resultSet.getString("title"),
            resultSet.getString("deskripsi"),
            resultSet.getInt("duration"),
            resultSet.getInt("jarak"),
            resultSet.getBytes("images"),
            resultSet.getInt("date"),
            resultSet.getInt("month"),
            resultSet.getInt("year")
        );
    }

    @Override
    public void deleteById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
