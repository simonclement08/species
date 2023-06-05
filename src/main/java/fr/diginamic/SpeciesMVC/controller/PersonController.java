package fr.diginamic.SpeciesMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.SpeciesMVC.model.Person;
import fr.diginamic.SpeciesMVC.repository.PersonRepository;
import jakarta.validation.Valid;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/person")
	public String listPersons(Model model) {
		List<Person> persons = personRepository.findAll();
		model.addAttribute("persons", persons);
		return "list_persons";
	}

	@GetMapping("/person/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			model.addAttribute(person.get());
			return "details_person";
		}
		return "error";
	}

	@GetMapping("/person/create")
	public String initCreate(Model model) {
		model.addAttribute(new Person());
		return "create_person";
	}

	@PostMapping("/person")
	public String createOrUpdate(@Valid Person person, BindingResult result) {
		this.personRepository.save(person);
		return "redirect:/person";
	}

	@GetMapping("/person/delete/{id}")
	public String delete(@PathVariable("id") Integer personId) {
		Optional<Person> personToDelete = this.personRepository.findById(personId);
		personToDelete.ifPresent(person -> this.personRepository.delete(person));
		return "redirect:/person";
	}
}
