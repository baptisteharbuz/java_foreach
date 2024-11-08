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

import com.example.model.Etudiant;
import com.example.model.Formateur;
import com.example.services.FormateurService;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// example /formateurs
// Controllers
@Controller
@RequestMapping("/formateur")
public class FormateurController {
    @Autowired
    private FormateurService formateurService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    // example /
    // Utilisateur va devoir aller sur /formateurs/
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(formateurService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET
    // example /{id}
    // Utilisateur va devoir aller sur /formateurs/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(formateurService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // example /
    // Utilisateur va devoir aller sur /formateurs/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Formateur formateur) {
        try {
            formateurService.insert(formateur);
            String jsonData = objectMapper.writeValueAsString("formateur ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez ceci pour afficher l'erreur dans la console
            return new ResponseEntity<>("Erreur lors de l'ajout de l'étudiant", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH/PUT
    // example /{id}
    // Utilisateur va devoir aller sur /formateurs/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Formateur formateur, @PathVariable("id") int id) {
        try {
            Formateur existingFormateur = formateurService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

            if (existingFormateur == null) {
                return new ResponseEntity<>("{\"error\": \"Formateur not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (formateur.getNom() != null)
                existingFormateur.setNom(formateur.getNom());
            if (formateur.getPrenom() != null)
                existingFormateur.setPrenom(formateur.getPrenom());
            if (formateur.getEmail() != null)
                existingFormateur.setEmail(formateur.getEmail());
            if (formateur.getTelephone() != null)
                existingFormateur.setTelephone(formateur.getTelephone());

            formateurService.update(existingFormateur);
            String jsonData = objectMapper.writeValueAsString("Formateur modifié");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de la mise à jour", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    // example /{id}
    // Utilisateur va devoir aller sur /formateurs/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            formateurService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Formateur supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de la suppression", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}