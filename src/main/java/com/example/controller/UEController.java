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

import com.example.model.UE;
import com.example.services.UEService;

// 5 TYPES DE REQUÊTES HTTP/HTTPS
// GET -> Récuperer des données
// POST -> Ajouter des données
// DELETE -> Supprimer des données 
// PATCH -> Modifier des données 
// PUT -> Modifier des données

// Préciser la route du controllers 
// example /ue
// Controllers
@Controller
@RequestMapping("/ue")
public class UEController {
    @Autowired
    private UEService ueService;
    @Autowired
    private ObjectMapper objectMapper;

    public UEController() {
        this.ueService = new UEService();
        this.objectMapper = new ObjectMapper();
    }

    // GET
    // example /
    // Utilisateur va devoir aller sur /ue/
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(ueService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET
    // example /{id}
    // Utilisateur va devoir aller sur /ue/1
    @GetMapping("/{id}")
    public ResponseEntity<String> getByID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(ueService.getByID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // example /
    // Utilisateur va devoir aller sur /ue/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody UE ue) {
        try {
            ueService.insert(ue);
            String jsonData = objectMapper.writeValueAsString("Unité d'enseignement ajoutée");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez ceci pour afficher l'erreur dans la console
            return new ResponseEntity<>("Erreur lors de l'ajout de l'unité d'enseignement",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PATCH/PUT
    // example /{id}
    // Utilisateur va devoir aller sur /ue/1
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UE ue, @PathVariable("id") int id) {
        try {
            UE existingUe = ueService.getByID(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            if (existingUe == null) {
                return new ResponseEntity<>("{\"error\": \"UE not found\"}", headers, HttpStatus.NOT_FOUND);
            }

            if (ue.getLibelle() != null)
                existingUe.setLibelle(ue.getLibelle());

            ueService.update(existingUe);
            String jsonData = objectMapper.writeValueAsString("Unité d'enseignement Modifiée");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Affiche les détails de l'erreur dans la console
            return new ResponseEntity<>("Erreur lors de la mise à jour", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    // example /{id}
    // Utilisateur va devoir aller sur /ue/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            ueService.delete(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>("{\"message\": \"Unité d'enseignement supprimée\"}", headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}