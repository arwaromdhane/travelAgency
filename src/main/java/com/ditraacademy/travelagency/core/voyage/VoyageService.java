package com.ditraacademy.travelagency.core.voyage;


import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.core.destination.DestinationRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity createVoyage(Voyage voyage) {
        Optional<Destination> destinationOptional= destinationRepository.findById(voyage.getDestination().getId());
        if(! destinationOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong destinationID Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
        voyage.setDestination(destinationOptional.get());
        if (voyage.getTitre() == null)
            return new ResponseEntity(new ErrorResponseModel("Titre Required"), HttpStatus.BAD_REQUEST);

        if (voyage.getDescription() == null)
            return new ResponseEntity(new ErrorResponseModel("Description required"), HttpStatus.BAD_REQUEST);

        if (voyage.getDate() == null)
            return new ResponseEntity(new ErrorResponseModel("date Required"), HttpStatus.BAD_REQUEST);

        if (voyage.getNbrPlaces() == null  )
            return new ResponseEntity(new ErrorResponseModel("nombre place invalide"), HttpStatus.BAD_REQUEST);
        if (voyage.getPrix() == 0)
            return new ResponseEntity(new ErrorResponseModel("prix invalide"), HttpStatus.BAD_REQUEST);

        voyage = voyageRepository.save(voyage);
        return new ResponseEntity<>(voyage, HttpStatus.OK);
    }

    public List<Voyage> getVoyages() {
        List<Voyage> voyageList = voyageRepository.findAll();
        return voyageList;

    }

    public List<Voyage> getVoyageByprix(double prix ) {
        List<Voyage> voyageList = voyageRepository.findVoyagesByPrixBeforeAndNbrPlacesNot( prix , 0);
        return voyageList;

    }

    public ResponseEntity<?> getVoyage( int id) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if(! voyageOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong voyage Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        Voyage voyage = voyageOptional.get();
        return new ResponseEntity<>(voyage, HttpStatus.OK); //retourner la classe +code statut
    }

    public ResponseEntity<?> UpdateVoyage( int id, Voyage VoyageUpdate) {
        //récupération de l'utiisateur by id
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if(! voyageOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong Voyage Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        Voyage databaseVoyage = voyageOptional.get();

        //la mise ajour se fait aprés avoir tester les données en entrer
        if (VoyageUpdate.getTitre() != null){
            if (VoyageUpdate.getTitre().length() <3){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong Voyage titre");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databaseVoyage.setTitre(VoyageUpdate.getTitre());}

        if (VoyageUpdate.getDescription() != null){
            if (VoyageUpdate.getDescription().length() <5){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong Voyage description");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databaseVoyage.setDescription(VoyageUpdate.getDescription());}

        if (VoyageUpdate.getNbrPlaces() != null){
            if (VoyageUpdate.getNbrPlaces() <5){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong nombre de places");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databaseVoyage.setNbrPlaces(VoyageUpdate.getNbrPlaces());}

        if (VoyageUpdate.getDate() == null){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong Date");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST);}
            databaseVoyage.setDate(VoyageUpdate.getDate());

        if (VoyageUpdate.getPrix() == null){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong price");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST);}
        databaseVoyage.setPrix(VoyageUpdate.getPrix());

        //enregistrer dans la base
        voyageRepository.save(databaseVoyage);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    public ResponseEntity<?> DeleteVoyage( int id) {
        //récupération de l'utiisateur by id
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if(! voyageOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong Voyage Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        voyageRepository.deleteById(id);
        //afficher tous le reste des Voyages
        List<Voyage> VoyageList = voyageRepository.findAll();
        return new ResponseEntity<>(VoyageList, HttpStatus.OK);
    }

    public ResponseEntity<?> DeleteAllVoyage() {
        if(voyageRepository.findAll() ==null){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong database vide");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
        voyageRepository.deleteAll();
        ErrorResponseModel errorResponseModel = new ErrorResponseModel("Suppression avec succées");
        return new ResponseEntity<>(errorResponseModel , HttpStatus.OK);
    }
}
