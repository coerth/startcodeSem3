/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import dtos.MovieDTO;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.*;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        //PersonFacade pf = PersonFacade.getInstance(emf);
       /* pf.create(new PersonDTO(new Person("Long", 21)));
        pf.create(new PersonDTO(new Person("Betul", 99)));
        pf.create(new PersonDTO(new Person("Denis", 18)));*/

        //EmployeeFacade ef = EmployeeFacade.getInstance(emf);
        /*ef.create(new EmployeeDTO(new Employee("Long", "en adresse", 25000)));
        ef.create(new EmployeeDTO(new Employee("Morten", "en anden adresse", 1500)));
        ef.create(new EmployeeDTO(new Employee("Betul", "en helt tredje adresse", 50000)));
        ef.create(new EmployeeDTO(new Employee("Betul", "en fjerde adresse", 100)));*/

        MovieFacade mf = MovieFacade.getInstance(emf);
       // mf.create(new MovieDTO(new Movie(2018, "Hukommelsestab", "Thriller")));
        Actor denis = new Actor("Denis");

        MovieDTO m1 = mf.getById(1);


    }
    
    public static void main(String[] args) {
        populate();
    }
}
