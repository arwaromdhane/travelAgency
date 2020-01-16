package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.core.voyage.Voyage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
// il sagi d'un probleme stackoverflow maneha pile tabet w fadhet ,, ahna lmochkla mtaena bin jzon w java class il sagit d'un boucle infinie,
    //w bch nsal7ou ce probleme nzidou jzonignore bch ytafi dhaw al attribut li manhbouchi yarja3, kima zeda kn nhebou motpasse mayarja3ch f methode get.
    //t'appliqi al attributs ou classe w tnehi cette attribut ml format jzone.jzone taml bch ykhabi attribut mou3ayn
    @JsonIgnore
    @OneToMany(mappedBy = "destination")
    //cette relation n'est pas obligatoire ema amalnaha bch rakahna bidirectionnelle
    //mapped by takhou chnoiwa samina lentité fl table loula , nsami nafsha f table2
    private List<Voyage> voyages;
}
