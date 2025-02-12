package com.example.tugasBesar.eventAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.servlet.http.HttpSession;

@Repository
public class eventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper untuk memetakan hasil query ke objek Input
    private static class InputRowMapper implements RowMapper<event> {
        @Override
        public event mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new event(
                    rs.getInt("idlomba"),
                    rs.getString("title"),
                    rs.getInt("tanggal"),
                    rs.getInt("bulan"),
                    rs.getInt("tahun"),
                    rs.getString("description"),
                    rs.getString("location"),
                    rs.getInt("range") 
            );
        }
    }

    // Menyimpan Input ke database
    public int save(event event, HttpSession session) {
        
        String sql = "INSERT INTO lomba (title, tanggal, bulan, tahun, description, location, range) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            event.getTitle(),
            event.getTanggal(),
            event.getBulan(),
            event.getTahun(),
            event.getDescription(),
            event.getLocation(),
            event.getRange()
        );
    }

    // Metode lainnya untuk query database (misalnya, mencari data input)
    public List<event> findAll() {
        String sql = "SELECT * FROM lomba";
        return jdbcTemplate.query(sql, new InputRowMapper());
    }

    public List<event> findByEventId(Integer eventId) {
        String sql = "SELECT * FROM lomba WHERE idlomba = ?";
        return jdbcTemplate.query(sql, new Object[]{eventId}, new InputRowMapper());
    }
}
