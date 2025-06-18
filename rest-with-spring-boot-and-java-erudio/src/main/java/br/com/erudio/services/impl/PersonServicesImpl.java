package br.com.erudio.services.impl;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServicesImpl implements PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository personRepository;


    @Override
    public List<Person> findAll() {
        logger.info("Finding all People");
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        logger.info("Finding by ID!");
        return personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
    }

    @Override
    public Person create(Person person) {
        logger.info("Created person");
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        logger.info("Update person");
       Person entity = personRepository.findById(person.getId())
               .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

       entity.setFirstName(person.getFirstName());
       entity.setLastName(person.getLastName());
       entity.setAddress(person.getAddress());
       entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        logger.info("Delete by ID!");
        Person entity = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }
}
