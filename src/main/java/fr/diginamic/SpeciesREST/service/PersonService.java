package fr.diginamic.SpeciesREST.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.diginamic.SpeciesREST.dto.PersonDto;
import fr.diginamic.SpeciesREST.exception.EntityNotFoundException;
import fr.diginamic.SpeciesREST.exception.EntityToCreateHasAnIdException;
import fr.diginamic.SpeciesREST.exception.EntityToUpdateHasNoIdException;
import fr.diginamic.SpeciesREST.mapper.PersonMapper;
import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.model.Person;
import fr.diginamic.SpeciesREST.repository.PersonRepository;
import jakarta.validation.Valid;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll() {
		return this.personRepository.findAll();
	}

	public Page<PersonDto> findAll(Pageable pageable) {
		Page<Person> pagePerson = this.personRepository.findAll(pageable);
		return pagePerson.map((person) -> PersonMapper.toDto(person));
	}

	public PersonDto findById(Integer id) {
		Person person = this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return PersonMapper.toDto(person);
	}

	public Person create(@Valid Person personToCreate) {
		if (personToCreate.getId() != null) {
			throw new EntityToCreateHasAnIdException();
		}
		return this.personRepository.save(personToCreate);
	}

	public Person update(@Valid Person personToUptade) {
		if (personToUptade.getId() == null) {
			throw new EntityToUpdateHasNoIdException();
		}
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
