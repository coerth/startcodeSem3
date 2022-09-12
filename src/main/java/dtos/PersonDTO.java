/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private long id;

    private String name;
    private int age;
    private String str1;
    private String str2;

    public PersonDTO(String dummyStr1, String dummyStr2) {
        this.str1 = dummyStr1;
        this.str2 = dummyStr2;
    }
    
    

    public PersonDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonDTO(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }

    public static List<PersonDTO> getDtos(List<Person> persons){
        List<PersonDTO> persondtos = new ArrayList();
        persons.forEach(person->persondtos.add(new PersonDTO(person)));
        return persondtos;
    }


    public PersonDTO(RenameMe person) {
        if(person.getId() != null)
            this.id = person.getId();
        this.str1 = person.getDummyStr1();
        this.str2 = person.getDummyStr2();
    }

    public String getDummyStr1() {
        return str1;
    }

    public void setDummyStr1(String dummyStr1) {
        this.str1 = dummyStr1;
    }

    public String getDummyStr2() {
        return str2;
    }

    public void setDummyStr2(String dummyStr2) {
        this.str2 = dummyStr2;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                '}';
    }
}
