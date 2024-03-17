package com.hsan.tuntrust.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "organisation_detail")
public class Representant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organisation_id")
    private Integer organisationId;
    @Column(unique = true)
    private Integer matricule;
    private String raisonSociale;
    private Categorie categorie;
    private PaysEnregistrement paysEnregistrement;
    private TypePaysEnregistrement typePaysEnregistrement;
    private String pdfRne;
    private String pdfCarteId;
    private String pdfRegistre;
    private String nom;
    private String codePostale;
    private String ville;
    private String gouvernement;
    private Integer tel;
    private Integer fax;
    private String adresse;
    private String pays;
    @Column(name = "confirmation_required")
    private boolean confirmationRequired;

}