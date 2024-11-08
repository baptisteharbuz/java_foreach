package com.example.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Absences;
import com.example.wrappers.AbsencesWrapper;

@Service
public class AbsencesService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AbsencesWrapper absencesWrapper;

    public List<Absences> getAll() {
        String sql = "SELECT * FROM Absences";
        return this.jdbcTemplate.query(sql, absencesWrapper);
    }

    public Absences getByID(int id) {
        String sql = "SELECT * FROM Absences WHERE id = ?";
        return this.jdbcTemplate.queryForObject(sql, absencesWrapper, id);
    }

    public int insert(Absences absences) {
        String sql = "INSERT INTO Absences(Date_Debut, Date_Fin, Type, FK_Etudiant) VALUES (?,?,?,?)";
        return this.jdbcTemplate.update(sql, absences.getDateDebut(), absences.getDateFin(), absences.getType(), absences.getEtudiants().getId());
    }

    public int update(Absences absences) {
        String sql = "UPDATE Absences SET Date_Debut = ?, Date_Fin = ?, Type = ?, FK_Etudiant = ? WHERE Id = ?";
        return this.jdbcTemplate.update(sql, absences.getDateDebut(), absences.getDateFin(), absences.getType(), absences.getEtudiants().getId(), absences.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM Absences WHERE Id = ?";
        return this.jdbcTemplate.update(sql, id);
    }
}