package br.com.erudio.services;

import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.model.Person;

import java.util.List;

public interface PersonServices {

    List<PersonDTO> findAll();

    PersonDTO findById(Long id);

    PersonDTO create(PersonDTO person);

    PersonDTO update(PersonDTO person);

    void delete(Long id);
}
