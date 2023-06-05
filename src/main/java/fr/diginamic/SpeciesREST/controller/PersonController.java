package fr.diginamic.SpeciesREST.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.SpeciesREST.model.Person;
import fr.diginamic.SpeciesREST.service.PersonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/")
	public List<Person> findAll() {
		return this.personService.findAll();
	}

//	@GetMapping("/")
//	public Page<Person> findAll(Pageable pageable) {
//		return this.personService.findAll(pageable);
//	}

	@GetMapping("/{id}")
	public Person findById(@PathVariable("id") Integer id) {
		return this.personService.findById(id);
	}

	@PostMapping
	public Person createPerson(@RequestBody @Valid Person personToCreate) {
		if (personToCreate.getId() != null) {
			throw new IllegalArgumentException("Impossible de créer une personne avec un ID");
		}
		return this.personService.create(personToCreate);
	}

	@PutMapping
	public Person updatePerson(@RequestBody @Valid Person personToUpdate) {
		if (personToUpdate.getId() == null) {
			throw new IllegalArgumentException("Impossible de mettre à jour une personne sans ID");
		}
		return this.personService.update(personToUpdate);
	}

	@DeleteMapping
	public void deletePerson(@RequestBody @Valid Person personToDelete) {
		this.personService.delete(personToDelete);
	}
}
