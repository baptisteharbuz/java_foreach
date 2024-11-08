package com.example.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.UE;

public class UEWrapper implements RowMapper<UE> {

    @Override
    public UE mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UE(rs.getInt("Id"), rs.getString("Libelle"));
    }
    
}
