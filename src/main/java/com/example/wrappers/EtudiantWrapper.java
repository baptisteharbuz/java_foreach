package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Etudiant;

public class EtudiantWrapper implements RowMapper<Etudiant> {

    @Override
    public Etudiant mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Etudiant(
            rs.getInt("Id"), 
            rs.getString("Nom"), 
            rs.getString("Prenom"), 
            rs.getString("Email"), 
            rs.getString("Telephone")
        );
    }
    
}
