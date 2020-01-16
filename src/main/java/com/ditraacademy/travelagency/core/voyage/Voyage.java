package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private Integer nbrPlaces;
    private Double prix;
    private Date date;
    //fema notion jdida zedneha w asasiya hiya ennou lmodel houwa class yaml mapping bin spring w APIclient , w entity hiya class zeda taml mapping bin DB w spring
// le nouveau de cette seance la relation bidirectionnelle andha avantage kbir lljpa, modification fl voyage w fl destination satrine

    @ManyToOne
  //cette relation est obligatoire bch tsir
    private Destination destination;
}
