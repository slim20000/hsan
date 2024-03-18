package com.hsan.tuntrust.controller;

import com.hsan.tuntrust.model.Organisation;
import com.hsan.tuntrust.model.Representant;
import com.hsan.tuntrust.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;

    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @GetMapping
    public ResponseEntity<List<Organisation>> getAllOrganisations() {
        List<Organisation> organisations = organisationService.getAllOrganisations();
        return ResponseEntity.ok(organisations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organisation> getOrganisationById(@PathVariable Integer id) {
        Optional<Organisation> organisation = organisationService.getOrganisationById(id);
        return organisation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Organisation> createOrganisation(@RequestBody Organisation organisation) {
        Organisation savedOrganisation = organisationService.saveOrganisation(organisation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganisation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organisation> updateOrganisation(@PathVariable Integer id, @RequestBody Organisation organisation) {
        Optional<Organisation> existingOrganisation = organisationService.getOrganisationById(id);
        if (existingOrganisation.isPresent()) {
            organisation.setOrganisationId(id);
            Organisation updatedOrganisation = organisationService.saveOrganisation(organisation);
            return ResponseEntity.ok(updatedOrganisation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Integer id) {
        Optional<Organisation> organisation = organisationService.getOrganisationById(id);
        if (organisation.isPresent()) {
            organisationService.deleteOrganisation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{organisationId}/representants")
    public ResponseEntity<List<Representant>> getAllRepresentantsForOrganisation(@PathVariable Integer organisationId) {
        List<Representant> representants = organisationService.getAllRepresentants(organisationId);
        return ResponseEntity.ok(representants);
    }

    @DeleteMapping("/{organisationId}/representants/{representantId}")
    public ResponseEntity<String> removeRepresentantFromOrganisation(@PathVariable Integer organisationId,
                                                                     @PathVariable Long representantId) {
        organisationService.removeRepresentant(organisationId, representantId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Representant removed from organisation successfully.");
    }
}