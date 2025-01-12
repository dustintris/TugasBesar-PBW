/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.tugasBesar.eventParticipant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.servlet.http.HttpSession;

@Repository
public class epRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper untuk memetakan hasil query ke objek Input
    private static class InputRowMapper implements RowMapper<eventParticipants> {
        @Override
        public eventParticipants mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new eventParticipants(
                    rs.getInt("idlomba"),
                    rs.getInt("userid")
            );
        }
    }

    // Menyimpan Input ke database
    public boolean save(eventParticipants event, HttpSession session) {
        Integer userid = (Integer) session.getAttribute("id");
        System.out.println("epRepo userid is " + userid);
        
        String sql = "INSERT INTO participants (eventid, userid) VALUES (?, ?)";
        
        int rowsAffected = jdbcTemplate.update(sql, event.getIdlomba(), userid);
        
        // Return true if at least one row was affected, indicating success
        return rowsAffected > 0;
    }

    // Metode lainnya untuk query database (misalnya, mencari data input)
    public List<eventParticipants> findAll() {
        String sql = "SELECT * FROM lomba";
        return jdbcTemplate.query(sql, new InputRowMapper());
    }

    public List<eventParticipants> findByEventId(Integer eventId) {
        String sql = "SELECT * FROM lomba WHERE idlomba = ?";
        return jdbcTemplate.query(sql, new Object[]{eventId}, new InputRowMapper());
    }
}
