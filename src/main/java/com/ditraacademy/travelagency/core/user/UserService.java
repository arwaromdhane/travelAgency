package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import com.ditraacademy.travelagency.utils.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Mailer mailer;

    public ResponseEntity createUser(User user) {
        if (user.getName() == null)
            return new ResponseEntity(new ErrorResponseModel("User name Required"), HttpStatus.BAD_REQUEST);

        if (user.getName().length() < 3)
            return new ResponseEntity(new ErrorResponseModel("User name invalide"), HttpStatus.BAD_REQUEST);

        if (user.getAge() == null)
            return new ResponseEntity(new ErrorResponseModel("User age Required"), HttpStatus.BAD_REQUEST);

        if (user.getAge() <= 0)
            return new ResponseEntity(new ErrorResponseModel("Userage invalide"), HttpStatus.BAD_REQUEST);
//les etapes de mail: 1-dependency 2-Mailer

        String destination = user.getEmail();
        String subject = " Welcom";
        String text = " hello " +user.getName();

        mailer.sendEmail(destination, subject , text);
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public List<User> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList;

    }

    public ResponseEntity<?> getUser( int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(! userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong user Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        User user = userOptional.get();
        return new ResponseEntity<>(user, HttpStatus.OK); //retourner la classe +code statut
    }

    public ResponseEntity<?> UpdateUser( int id, User userUpdate) {
        //récupération de l'utiisateur by id
        Optional<User> userOptional = userRepository.findById(id);
        if(! userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong user Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        User databaseUser = userOptional.get();

        //la mise ajour se fait aprés avoir tester les données en entrer
        if (userUpdate.getName() != null){
            if (userUpdate.getName().length() <3){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong user name");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databaseUser.setName(userUpdate.getName());}

        if (userUpdate.getAge() != null){
            if (userUpdate.getAge() <10){
                ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong user Age");
                return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
            databaseUser.setAge(userUpdate.getAge());}


        //enregistrer dans la base
        userRepository.save(databaseUser);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    public ResponseEntity<?> DeleteUser( int id) {
        //récupération de l'utiisateur by id
        Optional<User> userOptional = userRepository.findById(id);

        if(! userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Wrong user Id");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }

        userRepository.deleteById(id);
        //afficher tous le reste des users
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<?> DeleteAllUser() {
        if(userRepository.findAll() ==null){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong database vide");
            return new ResponseEntity<>(errorResponseModel , HttpStatus.BAD_REQUEST); }
        userRepository.deleteAll();
        ErrorResponseModel errorResponseModel = new ErrorResponseModel("Suppression avec succées");
        return new ResponseEntity<>(errorResponseModel , HttpStatus.OK);
    }
}
