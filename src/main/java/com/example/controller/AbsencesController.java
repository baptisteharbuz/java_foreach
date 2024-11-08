package com.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Absences;
import com.example.services.AbsencesService;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// example /absences
// Controllers
@Controller
@RequestMapping("/absences")
public class AbsencesController {
    @Autowired
    private AbsencesService absencesService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    // example /
    // Utilisateur va devoir aller sur /absences/
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(absencesService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET
    // example /{id}
    // Utilisateur va devoir aller sur /absences/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(absencesService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // example /
    // Utilisateur va devoir aller sur /absences/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Absences absences) {
        absencesService.insert(absences);
        return new ResponseEntity<>("{\"message\": \"Absence ajoutée\"}", HttpStatus.CREATED);
    }

    // PATCH/PUT
    // example /{id}
    // Utilisateur va devoir aller sur /absences/1
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Absences absences, @PathVariable("id") int id) {
        absences.setId(id);
        absencesService.update(absences);
        return new ResponseEntity<>("{\"message\": \"Absence mise à jour\"}", HttpStatus.OK);
    }

    // DELETE
    // example /{id}
    // Utilisateur va devoir aller sur /absences/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        absencesService.delete(id);
        return new ResponseEntity<>("{\"message\": \"Absence supprimée\"}", HttpStatus.OK);
    }
}
