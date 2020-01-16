package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.hotel.Hotel;

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
public class Chambre  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;

    @ManyToOne
    private CategorieChambre categorie;

    @ManyToOne
    private TypeChambre type;


    //bch namlou tawa relation manytomany , nzidou satrin hadhom
   @JsonIgnore
    @ManyToMany(mappedBy = "chambres")
    private List<Hotel> hotels;

}
