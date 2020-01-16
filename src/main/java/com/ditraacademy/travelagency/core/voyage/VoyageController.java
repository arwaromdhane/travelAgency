package com.ditraacademy.travelagency.core.voyage;
import com.ditraacademy.travelagency.core.user.User;
import com.ditraacademy.travelagency.core.user.UserRepository;
import com.ditraacademy.travelagency.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoyageController {

    @Autowired
    VoyageRepository voyageRepository;

    @Autowired
    VoyageService voyageService;

    //post envoie les données dans un body
    @PostMapping("/voyage")
    public ResponseEntity createVoyage(@RequestBody Voyage voyage) {
        return voyageService.createVoyage(voyage);
    }

    @GetMapping("/voyages")
    public List<Voyage> getVoyages() {
        return voyageService.getVoyages();
    }


    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> getVoyage(@PathVariable int id) {
        return voyageService.getVoyage(id);
    }


    @GetMapping("/voyag")
    public List<Voyage> getVoyageByprix(@RequestParam double prix ) {
        return voyageService.getVoyageByprix(prix );
    }



    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> UpdateVoyage(@PathVariable int id, @RequestBody Voyage voyageUpdate) {
        return voyageService.UpdateVoyage(id, voyageUpdate);
    }

    //Delete user if exist
    @DeleteMapping("/voyage/{id}")
    public ResponseEntity<?> DeleteVoyage(@PathVariable int id) {
        return voyageService.DeleteVoyage(id);
    }


}
