package com.example.tugasBesar.kegiatan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class kegiatanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class InputRowMapper implements RowMapper<kegiatan> {
        @Override
        public kegiatan mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new kegiatan(
                    rs.getInt("idinput"),
                    rs.getString("username"),
                    rs.getInt("jarak"),
                    rs.getInt("durasi"),
                    rs.getInt("tanggal"),
                    rs.getInt("bulan"),
                    rs.getInt("tahun"),
                    rs.getString("olahraga"),
                    rs.getString("judul"),
                    rs.getString("deskripsi"),
                    rs.getString("imagepath")
            );
        }
    }

    // Query untuk mengambil semua data berdasarkan username
    public List<kegiatan> findByUsername(String username) {
        String sql = "SELECT * FROM input WHERE username = ?";
        return jdbcTemplate.query(sql, new InputRowMapper(), username);
    }

    // Query untuk mengambil semua data
    public List<kegiatan> findAll() {
        String sql = "SELECT * FROM input";
        return jdbcTemplate.query(sql, new InputRowMapper());
    }
}