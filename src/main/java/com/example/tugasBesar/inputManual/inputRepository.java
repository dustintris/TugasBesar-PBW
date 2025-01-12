package com.example.tugasBesar.inputManual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Repository
public class inputRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper untuk memetakan hasil query ke objek Input
    private static class InputRowMapper implements RowMapper<input> {
        @Override
        public input mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new input(
                    rs.getInt("idinput"),
                    rs.getInt("jarak"),
                    rs.getInt("durasi"),
                    rs.getInt("tanggal"),
                    rs.getInt("bulan"),
                    rs.getInt("tahun"),
                    rs.getString("olahraga"),
                    rs.getString("judul"),
                    rs.getString("deskripsi"),
                    rs.getString("imagePath")  // Ambil imagePath dari database
            );
        }
    }

    // Menyimpan Input ke database
    public int save(input input, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Integer id = (Integer) session.getAttribute("id");

        if (id == null) {
            throw new IllegalArgumentException("User  ID is not found in the session.");
        }

        String sql = "INSERT INTO input (username, jarak, durasi, tanggal, bulan, tahun, olahraga, judul, deskripsi, imagePath, userid) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                username,
                input.getJarak(),
                input.getDurasi(),
                input.getTanggal(),
                input.getBulan(),
                input.getTahun(),
                input.getOlahraga(),
                input.getJudul(),
                input.getDeskripsi(),
                input.getImagePath(),  // Menyimpan hanya path gambar
                id
        );
    }

    // Metode untuk mencari semua input berdasarkan userId
    public List<input> findByUserId(Integer userId) {
        String sql = "SELECT * FROM input WHERE userid = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new InputRowMapper());
    }

    // Metode lainnya untuk query database (misalnya, mencari data input)
    public List<input> findAll() {
        String sql = "SELECT * FROM input";
        return jdbcTemplate.query(sql, new InputRowMapper());
    }

    public input findById(int id) {
        String sql = "SELECT * FROM input WHERE idinput = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new InputRowMapper());
    }
}