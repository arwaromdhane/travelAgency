package com.ditraacademy.travelagency.core.user;

        import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User,Integer> {

// l'interface contient seulement la signature de methode sans implementation ,, le JPA c'st lui qui transforme en sql et on édite dans proprietis
        //show jpa requete =true ,, on peux voir dans
}
