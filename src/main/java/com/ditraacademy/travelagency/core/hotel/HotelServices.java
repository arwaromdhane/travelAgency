package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.core.chambre.chambre.ChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired //importe yekhdemha jpa wahdou  //owverride : redifinition mta3 haja mawjouda
    ChambreRepository chambreRepository;

    public ResponseEntity<?> createHotel(Hotel hotel) {

        for (Chambre chambre : hotel.getChambres()){
            Optional<Chambre> chambreOptional = chambreRepository.findById(chambre.getId());
            if(!chambreOptional.isPresent()){
                return  new ResponseEntity<>(new ErrorResponseModel("wrong roomid:" + chambre.getId()), HttpStatus.BAD_REQUEST);
            }
        }
        hotel = hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return new ResponseEntity<>(hotelList, HttpStatus.OK);
    }
}
