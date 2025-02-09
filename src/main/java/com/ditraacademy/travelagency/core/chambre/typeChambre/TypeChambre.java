package com.ditraacademy.travelagency.core.chambre.typeChambre;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class TypeChambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id ;

    private String type ;
}
