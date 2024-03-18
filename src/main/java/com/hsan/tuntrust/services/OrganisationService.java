package com.hsan.tuntrust.services;

import com.hsan.tuntrust.model.Organisation;
import com.hsan.tuntrust.model.Representant;
import com.hsan.tuntrust.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    @Autowired
    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();
    }

    public Optional<Organisation> getOrganisationById(Integer id) {
        return organisationRepository.findById(id);
    }

    public Organisation saveOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public void deleteOrganisation(Integer id) {
        organisationRepository.deleteById(id);
    }

    public List<Representant> getAllRepresentants(Integer organisationId) {
        Optional<Organisation> optionalOrganisation = organisationRepository.findById(organisationId);
        if (optionalOrganisation.isPresent()) {
            Organisation organisation = optionalOrganisation.get();
            return organisation.getRepresentants();
        } else {
            throw new IllegalArgumentException("Organisation not found with id: " + organisationId);
        }
    }

    public void removeRepresentant(Integer organisationId, Long representantId) {
        Optional<Organisation> optionalOrganisation = organisationRepository.findById(organisationId);
        if (optionalOrganisation.isPresent()) {
            Organisation organisation = optionalOrganisation.get();
            organisation.getRepresentants().removeIf(representant -> representant.getId().equals(representantId));
            organisationRepository.save(organisation);
        } else {
            throw new IllegalArgumentException("Organisation not found with id: " + organisationId);
        }
    }

}