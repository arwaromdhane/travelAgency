package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity createDestination(Destination destination) {
        if (destination.getTitre() == null)
            return new ResponseEntity(new ErrorResponseModel("destination name Required"), HttpStatus.BAD_REQUEST);

        if (destination.getDescription() == null)
            return new ResponseEntity(new ErrorResponseModel("destination age Required"), HttpStatus.BAD_REQUEST);


        destination = destinationRepository.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    public List<Destination> getDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationList;

    }

    public ResponseEntity<?> getDestination( int id) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);

        if(! destinationOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        Destination destination = destinationOptional.get();
        return new ResponseEntity<>(destination, HttpStatus.OK); //retourner la classe +code statut
    }

    public ResponseEntity<?> UpdateDestination( int id, Destination destinationUpdate) {
        //récupération de l'utiisateur by id
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if(! destinationOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        Destination databasedestination = destinationOptional.get();

        //la mise ajour se fait aprés avoir tester les données en entrer
        if (destinationUpdate.getTitre() != null){
            if (destinationUpdate.getTitre().length() <3){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong destination titre");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databasedestination.setTitre(destinationUpdate.getTitre());}

        if (destinationUpdate.getDescription() != null){
            if (destinationUpdate.getDescription().length() <5){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong destination description");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databasedestination.setDescription(destinationUpdate.getDescription());}

        //enregistrer dans la base
        destinationRepository.save(databasedestination);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDestination( int id) {
        //récupération de l'utiisateur by id
        Optional<Destination> destinationOptional = destinationRepository.findById(id);

        if(! destinationOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong destination Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        destinationRepository.deleteById(id);
        //afficher tous le reste des destinations
        List<Destination> destinationList = destinationRepository.findAll();
        return new ResponseEntity<>(destinationList, HttpStatus.OK);
    }


}
