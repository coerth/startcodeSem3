/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getInstance(emf);
        pf.create(new PersonDTO(new Person("Long", 21)));
        pf.create(new PersonDTO(new Person("Betul", 99)));
        pf.create(new PersonDTO(new Person("Denis", 18)));


    }
    
    public static void main(String[] args) {
        populate();
    }
}
