package br.com.erudio.services;

import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.data.dto.v2.PersonDTOV2;

import java.util.List;

public interface PersonServices {

    List<PersonDTO> findAll();

    PersonDTO findById(Long id);

    PersonDTO create(PersonDTO person);

    PersonDTOV2 createV2(PersonDTOV2 person);

    PersonDTO update(PersonDTO person);

    void delete(Long id);
}
