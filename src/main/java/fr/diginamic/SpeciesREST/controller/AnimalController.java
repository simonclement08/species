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

import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/animal")
public class AnimalController {

	@Autowired
	AnimalService animalService;

//	@GetMapping
//	public List<Animal> findAll() {
//		return this.animalService.findAll();
//	}

	@GetMapping
	public Page<Animal> findAll(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {
		return this.animalService.findAll(PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public Animal findById(@PathVariable("id") Integer id) {
		return this.animalService.findById(id);
	}

	@PostMapping
	public Animal createAnimal(@RequestBody @Valid Animal animalToCreate) {
		return this.animalService.create(animalToCreate);
	}

	@PutMapping
	public Animal updateAnimal(@RequestBody @Valid Animal animalToUpdate) {
		return this.animalService.update(animalToUpdate);
	}

	@DeleteMapping
	public void deleteAnimal(@RequestBody @Valid Animal animalToDelete) {
		this.animalService.delete(animalToDelete);
	}
}
