package fr.diginamic.SpeciesMVC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.SpeciesMVC.enums.Sex;
import fr.diginamic.SpeciesMVC.model.Animal;
import fr.diginamic.SpeciesMVC.repository.AnimalRepository;
import fr.diginamic.SpeciesMVC.repository.SpeciesRepository;
import jakarta.validation.Valid;

@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("/animal")
	public String listAnimals(Model model) {
		List<Animal> animals = animalRepository.findAll();
		model.addAttribute("animals", animals);
		return "list_animals";
	}

	@GetMapping("/animal/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animal = animalRepository.findById(id);
		if (animal.isPresent()) {
			model.addAttribute(animal.get());
			return "details_animal";
		}
		return "error";
	}

	@GetMapping("/animal/create")
	public String initCreate(Model model) {
		model.addAttribute(new Animal());
		model.addAttribute("sexs", Sex.values());
		model.addAttribute("speciesList", speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		return "create_animal";
	}

	@PostMapping("/animal")
	public String createOrUpdate(@Valid Animal animal, BindingResult result) {
		this.animalRepository.save(animal);
		return "redirect:/animal";
	}

	@GetMapping("/animal/delete/{id}")
	public String delete(@PathVariable("id") Integer animalId) {
		Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
		animalToDelete.ifPresent(animal -> this.animalRepository.delete(animal));
		return "redirect:/animal";
	}
}
