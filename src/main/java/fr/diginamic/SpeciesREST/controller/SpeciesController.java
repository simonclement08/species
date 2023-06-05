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

import fr.diginamic.SpeciesREST.model.Species;
import fr.diginamic.SpeciesREST.service.SpeciesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/species")
public class SpeciesController {

	@Autowired
	SpeciesService speciesService;

//	@GetMapping("/")
//	public List<Species> findAll() {
//		return this.speciesService.findAll();
//	}

	@GetMapping("/")
	public Page<Species> findAll(Pageable pageable) {
		return this.speciesService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Species findById(@PathVariable("id") Integer id) {
		return this.speciesService.findById(id);
	}

	@PostMapping
	public Species createSpecies(@RequestBody @Valid Species speciesToCreate) {
		if (speciesToCreate.getId() != null) {
			throw new IllegalArgumentException("Impossible de créer une espèce avec un ID");
		}
		return this.speciesService.create(speciesToCreate);
	}

	@PutMapping
	public Species updateSpecies(@RequestBody @Valid Species speciesToUpdate) {
		if (speciesToUpdate.getId() == null) {
			throw new IllegalArgumentException("Impossible de mettre à jour une espèce sans ID");
		}
		return this.speciesService.update(speciesToUpdate);
	}

	@DeleteMapping
	public void deleteSpecies(@RequestBody @Valid Species speciesToDelete) {
		this.speciesService.delete(speciesToDelete);
	}
}
