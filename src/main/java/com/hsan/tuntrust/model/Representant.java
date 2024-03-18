package com.hsan.tuntrust.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Representant")
public class Representant {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String nationalite;

    @Enumerated(EnumType.STRING)
    private PieceIdentite pieceIdentite;

    private String numero;
    private String pdfPieceIdentite;
    private String tel;
    private String email;
    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;


}