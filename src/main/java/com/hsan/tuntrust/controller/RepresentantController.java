package com.hsan.tuntrust.controller;

import com.hsan.tuntrust.model.Organisation;
import com.hsan.tuntrust.model.Representant;
import com.hsan.tuntrust.services.RepresentantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/representants")
public class RepresentantController {
    private final RepresentantService representantService;

    @Autowired
    public RepresentantController(RepresentantService representantService) {
        this.representantService = representantService;
    }

    @PostMapping
    public ResponseEntity<Representant> createRepresentant(@RequestBody Representant representant) {
        Representant createdRepresentant = representantService.createRepresentant(representant);
        return new ResponseEntity<>(createdRepresentant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Representant> getRepresentantById(@PathVariable Long id) {
        Optional<Representant> optionalRepresentant = representantService.getRepresentantById(id);
        return optionalRepresentant.map(representant -> new ResponseEntity<>(representant, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Representant>> getAllRepresentants() {
        List<Representant> representants = representantService.getAllRepresentants();
        return new ResponseEntity<>(representants, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Representant> updateRepresentant(@PathVariable Long id, @RequestBody Representant updatedRepresentant) {
        Representant updated = representantService.updateRepresentant(id, updatedRepresentant);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepresentant(@PathVariable Long id) {
        representantService.deleteRepresentant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{representantId}/assign-organisation")
    public ResponseEntity<Void> assignRepresentantToOrganisation(@PathVariable Long representantId, @RequestBody Organisation organisation) {
        representantService.assignRepresentantToOrganisation(representantId, organisation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}