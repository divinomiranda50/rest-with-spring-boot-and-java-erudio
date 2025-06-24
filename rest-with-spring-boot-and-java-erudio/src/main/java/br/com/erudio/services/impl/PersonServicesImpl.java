package br.com.erudio.services.impl;

import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.data.dto.v2.PersonDTOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import static br.com.erudio.mapper.ObjectMapper.parseListObjects;
import static br.com.erudio.mapper.ObjectMapper.parseObject;

import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.services.PersonServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServicesImpl implements PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper converter;

    @Override
    public List<PersonDTO> findAll() {
        logger.info("Finding all People");
        return parseListObjects( personRepository.findAll(), PersonDTO.class );
    }

    @Override
    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");
        var entity = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }

    @Override
    public PersonDTO create(PersonDTO person) {
        logger.info("Created person");
        return parseObject(personRepository.save(parseObject(person, Person.class)), PersonDTO.class);
    }

    @Override
    public PersonDTO update(PersonDTO person) {
        logger.info("Update person");
       Person entity = personRepository.findById(person.getId())
               .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

       entity.setFirstName(person.getFirstName());
       entity.setLastName(person.getLastName());
       entity.setAddress(person.getAddress());
       entity.setGender(person.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    @Override
    public void delete(Long id) {
        logger.info("Delete by ID!");
        Person entity = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }
}
