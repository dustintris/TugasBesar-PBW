package com.example.tugasBesar.inputManual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
                    rs.getString("username"),
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
    public int save(input input) {
        String sql = "INSERT INTO input (username, jarak, durasi, tanggal, bulan, tahun, olahraga, judul, deskripsi, imagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            input.getUsername(),
            input.getJarak(),
            input.getDurasi(),
            input.getTanggal(),
            input.getBulan(),
            input.getTahun(),
            input.getOlahraga(),
            input.getJudul(),
            input.getDeskripsi(),
            input.getImagePath()  // Menyimpan hanya path gambar
        );
    }

    // Metode lainnya untuk query database (misalnya, mencari data input)
    public List<input> findAll() {
        String sql = "SELECT * FROM input";
        return jdbcTemplate.query(sql, new InputRowMapper());
    }
}
