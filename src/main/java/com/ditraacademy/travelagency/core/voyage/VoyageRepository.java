package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.voyage.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoyageRepository extends JpaRepository<Voyage,Integer> {

   // List<Voyage> findVoyagesByPrixBeforeOrderByPrix(double prix);

    List<Voyage> findVoyagesByPrixBeforeAndNbrPlacesNot(double prix, int i);
}
