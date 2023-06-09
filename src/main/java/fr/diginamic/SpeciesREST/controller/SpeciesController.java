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

import fr.diginamic.SpeciesREST.model.Species;
import fr.diginamic.SpeciesREST.service.SpeciesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/species")
public class SpeciesController {

	@Autowired
	SpeciesService speciesService;

//	@GetMapping
//	public List<Species> findAll() {
//		return this.speciesService.findAll();
//	}

	@GetMapping
	public Page<Species> getfindAll(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {
		return this.speciesService.findAll(PageRequest.of(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public Species findById(@PathVariable("id") Integer id) {
		return this.speciesService.findById(id);
	}

	@PostMapping
	public Species createSpecies(@RequestBody @Valid Species speciesToCreate) {
		return this.speciesService.create(speciesToCreate);
	}

	@PutMapping
	public Species updateSpecies(@RequestBody @Valid Species speciesToUpdate) {
		return this.speciesService.update(speciesToUpdate);
	}

	@DeleteMapping
	public void deleteSpecies(@RequestBody @Valid Species speciesToDelete) {
		this.speciesService.delete(speciesToDelete);
	}
}
