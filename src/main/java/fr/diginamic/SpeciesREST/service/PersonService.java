package fr.diginamic.SpeciesREST.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.model.Person;
import fr.diginamic.SpeciesREST.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll() {
		return this.personRepository.findAll();
	}

	public Page<Person> findAll(Pageable pageable) {
		return this.personRepository.findAll(pageable);
	}

	public Person findById(Integer id) {
		return this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Person create(@Valid Person personToCreate) {
		return this.personRepository.save(personToCreate);
	}

	public Person update(@Valid Person personToUptade) {
		return this.personRepository.save(personToUptade);
	}

	public void delete(@Valid Person personToDelete) {
		this.personRepository.delete(personToDelete);
	}

	public List<Person> findByLastnameOrFirstname(String lastname, String firstname) {
		return this.personRepository.findByLastnameOrFirstname(lastname, firstname);
	}

	public List<Person> findByAgeGreaterThanEqual(Integer age) {
		return this.personRepository.findByAgeGreaterThanEqual(age);
	}

	public List<Person> findPersonWhereAgeBetween(@Param("min") int min, @Param("max") int max) {
		return this.personRepository.findPersonWhereAgeBetween(min, max);
	}

	public List<Person> findOwnersOfAnimal(@Param("animal") Animal animal) {
		return this.personRepository.findOwnersOfAnimal(animal);
	}

	public void deletePersonsWithoutAnimal() {
		this.personRepository.deletePersonsWithoutAnimal();
	}

	public void insertRandomPersons(int x) {
		this.personRepository.insertRandomPersons(x);
	}
}
