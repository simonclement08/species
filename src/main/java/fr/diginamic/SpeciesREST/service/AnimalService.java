package fr.diginamic.SpeciesREST.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.SpeciesREST.enums.Sex;
import fr.diginamic.SpeciesREST.exception.EntityNotFoundException;
import fr.diginamic.SpeciesREST.exception.EntityToCreateHasAnIdException;
import fr.diginamic.SpeciesREST.exception.EntityToUpdateHasNoIdException;
import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.model.Species;
import fr.diginamic.SpeciesREST.repository.AnimalRepository;
import jakarta.validation.Valid;

@Service
public class AnimalService {

	@Autowired
	AnimalRepository animalRepository;

	public List<Animal> findAll() {
		return this.animalRepository.findAll();
	}

	public Page<Animal> findAll(Pageable pageable) {
		return this.animalRepository.findAll(pageable);
	}

	public Animal findById(Integer id) {
		return this.animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Animal create(@Valid Animal animalToCreate) {
		if (animalToCreate.getId() != null) {
			throw new EntityToCreateHasAnIdException();
		}
		return this.animalRepository.save(animalToCreate);
	}

	public Animal update(@Valid Animal animalToUptade) {
		if (animalToUptade.getId() == null) {
			throw new EntityToUpdateHasNoIdException();
		}
		return this.animalRepository.save(animalToUptade);
	}

	public void delete(@Valid Animal animalToDelete) {
		this.animalRepository.delete(animalToDelete);
	}

	public List<Animal> findBySpecies(Species species) {
		return this.animalRepository.findBySpecies(species);
	}

	public List<Animal> findByColorIn(List<String> colors) {
		return this.animalRepository.findByColorIn(colors);
	}

	public int countBySex(Sex sex) {
		return this.animalRepository.countBySex(sex);
	}

	public boolean isOwned(Animal animal) {
		return this.animalRepository.isOwned(animal);
	}

}
