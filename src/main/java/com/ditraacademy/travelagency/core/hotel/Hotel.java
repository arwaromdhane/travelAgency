package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update hotel SET deleted = true Where id = ?")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;

    private String nom;

    private String description;

    private Integer etoiles;

    private String adresse;

    private String telephone;

    @ManyToMany
    private List<Chambre> chambres;

}
