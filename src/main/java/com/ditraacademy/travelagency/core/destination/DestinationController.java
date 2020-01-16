package com.ditraacademy.travelagency.core.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DestinationController {


    @Autowired
    DestinationService destinationService;

    //post envoie les données dans un body
    @PostMapping("/destination")
    public ResponseEntity createDestination(@RequestBody Destination destination) {
        return destinationService.createDestination( destination);
    }

    @GetMapping("/destinations")
    public List<Destination> getDestinations() {
        return destinationService.getDestinations();
    }


    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getVoyage(@PathVariable int id) {
        return destinationService.getDestination(id);
    }

    @PutMapping("/destination/{id}")
    //update et retourner l'objet avec les nouvellles valeurs
    public ResponseEntity<?> UpdateVoyage(@PathVariable int id, @RequestBody Destination destinationUpdate) {
        return destinationService.UpdateDestination(id, destinationUpdate);
    }

    //Delete user if exist
    @DeleteMapping("/destination/{id}")
    public ResponseEntity<?> DeleteVoyage(@PathVariable int id) {
        return destinationService.deleteDestination(id);
    }

}
