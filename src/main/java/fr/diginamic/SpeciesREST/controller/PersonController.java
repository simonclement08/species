package fr.diginamic.SpeciesREST.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.SpeciesREST.dto.PersonDto;
import fr.diginamic.SpeciesREST.model.Person;
import fr.diginamic.SpeciesREST.service.PersonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

	@Autowired
	PersonService personService;

//	@GetMapping
//	public List<Person> findAll() {
//		return this.personService.findAll();
//	}

	@GetMapping
	public Page<PersonDto> findAll(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {
		return this.personService.findAll(PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public PersonDto findById(@PathVariable("id") Integer id) {
		return this.personService.findById(id);
	}

	@PostMapping
	public Person createPerson(@RequestBody @Valid Person personToCreate) {
		return this.personService.create(personToCreate);
	}

	@PutMapping
	public Person updatePerson(@RequestBody @Valid Person personToUpdate) {
		return this.personService.update(personToUpdate);
	}

	@DeleteMapping
	public void deletePerson(@RequestBody @Valid Person personToDelete) {
		this.personService.delete(personToDelete);
	}
}
