package com.ditraacademy.travelagency.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// mecanisme jpa , mayfasakhch , dima yaml update :spring.jpa.hibernate.ddl-auto=update
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userServices;

    //post envoie les données dans un body
    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userServices.getUsers();
    }

    //teste de validation (1ére cas)
    //cette methode feha barcha hajet jdod : 1-on a éliminer else pour simplifier le code..
    // 2-on prend le cas défavorable avant dans if
    //3-retourner une classe d'erreur avec le msg qu'on veut
    //4-on retourne de type ResponseEntity qui accepte tous type d'objet , on spécifiant l'objet
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
      return userServices.getUser(id);
    }

    @PutMapping("/user/{id}")
    //update et retourner l'objet avec les nouvellles valeurs
    public ResponseEntity<?> UpdateUser(@PathVariable int id, @RequestBody User userUpdate) {
     return userServices.UpdateUser(id, userUpdate);
    }

    //Delete user if exist
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable int id) {
        return userServices.DeleteUser(id);
    }
    //Delete all
    @DeleteMapping("/user")
    public ResponseEntity<?> DeleteAllUser() {
       return userServices.DeleteAllUser();
    }
}