package com.hsan.tuntrust.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "organisation_detail")
public class Organisation {
    @Id
    @JsonIgnore
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
    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
    private List<Representant> representants;
}