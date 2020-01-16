package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambreRepository;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreServices {

    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    TypeChambreRepository typeChambreRepository;

    @Autowired
    CategorieChambreRepository categorieChambreRepository;

    public ResponseEntity<?> createChambre(Chambre chambre) {

        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(chambre.getCategorie().getId());

        if(!categorieChambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("category not found"), HttpStatus.BAD_REQUEST);

        Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(chambre.getType().getId());

        if(!typeChambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("type not found"), HttpStatus.BAD_REQUEST);

        Optional<Chambre> chambreOptional = chambreRepository.findByCategorieAndType(chambre.getCategorie(), chambre.getType());

        if(chambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("chambre exist"), HttpStatus.BAD_REQUEST);

        chambre = chambreRepository.save(chambre);
        return new ResponseEntity<>(chambre, HttpStatus.OK);
    }

    public ResponseEntity<?> getChambres() {
        List<Chambre> chambres = chambreRepository.findAll();
        return new ResponseEntity<>(chambres, HttpStatus.OK);
    }
}
