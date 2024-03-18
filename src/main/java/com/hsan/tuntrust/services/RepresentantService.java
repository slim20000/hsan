package com.hsan.tuntrust.services;

import com.hsan.tuntrust.model.Organisation;
import com.hsan.tuntrust.model.Representant;
import com.hsan.tuntrust.repository.RepresentantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentantService {
    private final RepresentantRepository representantRepository;

    @Autowired
    public RepresentantService(RepresentantRepository representantRepository) {
        this.representantRepository = representantRepository;
    }

    public Representant createRepresentant(Representant representant) {
        return representantRepository.save(representant);
    }

    public Optional<Representant> getRepresentantById(Long id) {
        return representantRepository.findById(id);
    }

    public List<Representant> getAllRepresentants() {
        return representantRepository.findAll();
    }

    public void deleteRepresentant(Long id) {
        representantRepository.deleteById(id);
    }

    public Representant updateRepresentant(Long id, Representant updatedRepresentant) {
        Optional<Representant> optionalRepresentant = representantRepository.findById(id);
        if (optionalRepresentant.isPresent()) {
            Representant representant = optionalRepresentant.get();
            representant.setNom(updatedRepresentant.getNom());
            representant.setPrenom(updatedRepresentant.getPrenom());
            representant.setDateNaissance(updatedRepresentant.getDateNaissance());
            representant.setNationalite(updatedRepresentant.getNationalite());
            representant.setPieceIdentite(updatedRepresentant.getPieceIdentite());
            representant.setNumero(updatedRepresentant.getNumero());
            representant.setPdfPieceIdentite(updatedRepresentant.getPdfPieceIdentite());
            representant.setTel(updatedRepresentant.getTel());
            representant.setEmail(updatedRepresentant.getEmail());
            return representantRepository.save(representant);
        } else {
            throw new IllegalArgumentException("Representant not found with id: " + id);
        }
    }

    public void assignRepresentantToOrganisation(Long representantId, Organisation organisation) {
        Optional<Representant> optionalRepresentant = representantRepository.findById(representantId);
        if (optionalRepresentant.isPresent()) {
            Representant representant = optionalRepresentant.get();
            representant.setOrganisation(organisation);
            representantRepository.save(representant);
        } else {
            throw new IllegalArgumentException("Representant not found with id: " + representantId);
        }
    }
}