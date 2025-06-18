package br.com.erudio.services;

import br.com.erudio.model.Person;

import java.util.List;

public interface PersonServices {

    List<Person> findAll();

    Person findById(Long id);

    Person create(Person person);

    Person update(Person person);

    void delete(Long id);
}
