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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Suivre;
import com.example.services.SuivreService;

@Controller
@RequestMapping("/suivre")
public class SuivreController {
    @Autowired
    private SuivreService suivreService;
    @Autowired
    private ObjectMapper objectMapper;

    // GET
    // example /
    // Utilisateur va devoir aller sur /suivre/
    @GetMapping
    public ResponseEntity<String> getAll() {
        try {
            String jsonData = objectMapper.writeValueAsString(suivreService.getAll());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET
    // example /{id}/suivre
    // Utilisateur va devoir aller sur /suivre/1/etudiant
    @GetMapping("/{id}/etudiant")
    public ResponseEntity<String> getBySuivreID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(suivreService.getByEtudiantID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET
    // example /{id}/cours
    // Utilisateur va devoir aller sur /suivre/1/cours
    @GetMapping("/{id}/cours")
    public ResponseEntity<String> getByCourID(@PathVariable("id") int id) {
        try {
            String jsonData = objectMapper.writeValueAsString(suivreService.getByCourID(id));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST
    // example /
    // Utilisateur va devoir aller sur /suivre/
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Suivre suivre) {
        suivreService.insert(suivre.getCours().getId(), suivre.getEtudiants().getId());
        return new ResponseEntity<>("{\"message\": \"Enregistrement ajouté\"}", HttpStatus.CREATED);
    }

    // DELETE
    // example /{id}/cours
    // Utilisateur va devoir aller sur /suivre/1/cours
    @DeleteMapping("/{id}/cours")
    public ResponseEntity<String> deleteByCourID(@PathVariable("id") int id) {
        suivreService.deleteAllByCourID(id);
        return new ResponseEntity<>("{\"message\": \"Enregistrements supprimés\"}", HttpStatus.OK);
    }

    // DELETE
    // example /{id}/etudiant
    // Utilisateur va devoir aller sur /suivre/1/etudiant
    @DeleteMapping("/{id}/etudiant")
    public ResponseEntity<String> deleteByEtudiantID(@PathVariable("id") int id) {
        suivreService.deleteAllByEtudiantID(id);
        return new ResponseEntity<>("{\"message\": \"Enregistrements supprimés\"}", HttpStatus.OK);
    }

    // DELETE
    // example /{idEtudiant}/{idCour}
    // Utilisateur va devoir aller sur /suivre/1/1
    @DeleteMapping("/{idEtudiant}/{idCour}")
    public ResponseEntity<String> deleteByEtudiantID(@PathVariable("idEtudiant") int idEtudiant, @PathVariable("idCour") int idCour) {
        try {
            suivreService.delete(idCour, idEtudiant);
            return new ResponseEntity<>("{\"message\": \"Enregistrement supprimé\"}", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();  // Log l'erreur pour plus de détails dans la console
            return new ResponseEntity<>("{\"message\": \"Erreur lors de la suppression : " + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
