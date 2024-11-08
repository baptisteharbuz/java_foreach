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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Cours;
import com.example.services.CoursService;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// example /cours
// Controllers
@Controller
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    private CoursService coursService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    // example /
    // Utilisateur va devoir aller sur /cours/
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(coursService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET
    // example /{id}
    // Utilisateur va devoir aller sur /cours/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(coursService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // example /
    // Utilisateur va devoir aller sur /cours/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Cours cours) {
        try {
            coursService.insert(cours);
            String jsonData = objectMapper.writeValueAsString("Cours ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de l'ajout du cours", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH/PUT
    // example /{id}
    // Utilisateur va devoir aller sur /cours/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Cours cours, @PathVariable("id") int id) {
        try {
            Cours existingCours = coursService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

            if (existingCours == null) {
                return new ResponseEntity<>("{\"error\": \"Cours not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (cours.getDateDebut() != null)
                existingCours.setDateDebut(cours.getDateDebut());
            if (cours.getDateFin() != null)
                existingCours.setDateFin(cours.getDateFin());
            if (cours.getUe() != null)
                existingCours.setUe(cours.getUe());
            if (cours.getFormateur() != null)
                existingCours.setFormateur(cours.getFormateur());
            if (cours.getEtudiants() != null)
                existingCours.setEtudiants(cours.getEtudiants());

            coursService.update(existingCours);
            String jsonData = objectMapper.writeValueAsString("Cours modifié");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de la mise à jour", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    // example /{id}
    // Utilisateur va devoir aller sur /cours/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            coursService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Cours supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de la suppression", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}