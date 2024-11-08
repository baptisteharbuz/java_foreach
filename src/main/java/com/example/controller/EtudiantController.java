package com.example.controller;

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
import com.example.services.EtudiantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// exemple /etudiants
// Controllers
@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(etudiantService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(etudiantService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // exemple /
    // Utilisateur va devoir aller sur /etudiants/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Etudiant etudiant) {
        try {
            etudiantService.insert(etudiant);
            String jsonData = objectMapper.writeValueAsString("Etudiant ajouté");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez ceci pour afficher l'erreur dans la console
            return new ResponseEntity<>("Erreur lors de l'ajout de l'étudiant", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH/PUT
    // exemple /{id}
    // Utilisateur va devoir aller sur /etudiants/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Etudiant etudiant, @PathVariable("id") int id) {
        try {
            Etudiant existingEtudiant = etudiantService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingEtudiant == null) {
                return new ResponseEntity<>("{\"error\": \"Etudiant not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (etudiant.getNom() != null)
                existingEtudiant.setNom(etudiant.getNom());
            if (etudiant.getPrenom() != null)
                existingEtudiant.setPrenom(etudiant.getPrenom());
            if (etudiant.getEmail() != null)
                existingEtudiant.setEmail(etudiant.getEmail());
            if (etudiant.getTelephone() != null)
                existingEtudiant.setTelephone(etudiant.getTelephone());

            etudiantService.update(existingEtudiant);
            String jsonData = objectMapper.writeValueAsString("Etudiant Modifié");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Affiche les détails de l'erreur dans la console
            return new ResponseEntity<>("Erreur lors de la mise à jour", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    // exemple /{id}
    // Utilisateur va devoir aller sur /etudiants/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            etudiantService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Etudiant supprimé\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}