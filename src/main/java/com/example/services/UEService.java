package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.UE;
import com.example.wrappers.UEWrapper;

@Service
public class UEService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UE> getAll() {
        String sql = "SELECT * FROM UE";
        return this.jdbcTemplate.query(sql, new UEWrapper());
    }

    public UE getByID(int id) {
        String sql = "SELECT * FROM UE WHERE Id = ?";
        return this.jdbcTemplate.queryForObject(sql, new UEWrapper(), id);
    }

    public int insert(UE ue) {
        String sql = "INSERT INTO UE(Libelle) VALUES (?)";
        return this.jdbcTemplate.update(sql, ue.getLibelle());
    }

    public int update(UE ue) {
        String sql = "UPDATE UE SET Libelle=? WHERE Id=?";
        return this.jdbcTemplate.update(sql, ue.getLibelle(), ue.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM UE WHERE Id=?";
        return this.jdbcTemplate.update(sql, id);
    }
}
