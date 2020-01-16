package com.ditraacademy.travelagency.core.chambre.chambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChambreController {

    @Autowired
    ChambreServices chambreServices;

    @PostMapping("/chambre")
    public ResponseEntity<?> createChambre(@RequestBody Chambre chambre) {
        return chambreServices.createChambre(chambre);
    }

    @GetMapping("/chambres")
    public ResponseEntity<?> getAllChambres(){
        return chambreServices.getChambres();
    }

}
