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

import fr.diginamic.SpeciesMVC.model.Species;
import fr.diginamic.SpeciesMVC.repository.SpeciesRepository;
import jakarta.validation.Valid;

@Controller
public class SpeciesController {

	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("/species")
	public String listSpecies(Model model) {
		List<Species> species = speciesRepository.findAll();
		model.addAttribute("species", species);
		return "list_species";
	}

	@GetMapping("/species/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Species> species = speciesRepository.findById(id);
		if (species.isPresent()) {
			model.addAttribute(species.get());
			return "details_species";
		}
		return "error";
	}

	@GetMapping("/species/create")
	public String initCreate(Model model) {
		model.addAttribute(new Species());
		return "create_species";
	}

	@PostMapping("/species")
	public String createOrUpdate(@Valid Species speciesItem, BindingResult result) {
		this.speciesRepository.save(speciesItem);
		return "redirect:/species";
	}

	@GetMapping("/species/delete/{id}")
	public String delete(@PathVariable("id") Integer speciesId) {
		Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesId);
		speciesToDelete.ifPresent(species -> this.speciesRepository.delete(species));
		return "redirect:/species";
	}
}
