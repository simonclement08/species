package fr.diginamic.SpeciesREST.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.diginamic.SpeciesREST.model.Species;
import fr.diginamic.SpeciesREST.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class SpeciesService {

	@Autowired
	SpeciesRepository speciesRepository;

	public List<Species> findAll() {
		return this.speciesRepository.findAll();
	}

	public Page<Species> findAll(Pageable pageable) {
		return this.speciesRepository.findAll(pageable);
	}

	public Species findById(Integer id) {
		return this.speciesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Species create(@Valid Species speciesToCreate) {
		return this.speciesRepository.save(speciesToCreate);
	}

	public Species update(@Valid Species speciesToUptade) {
		return this.speciesRepository.save(speciesToUptade);
	}

	public void delete(@Valid Species speciesToDelete) {
		this.speciesRepository.delete(speciesToDelete);
	}

	public Species findFirstByCommonName(String commonName) {
		return this.speciesRepository.findFirstByCommonName(commonName);
	}

	public List<Species> findByLatinNameContainsIgnoreCase(String latinName) {
		return this.speciesRepository.findByLatinNameContainsIgnoreCase(latinName);
	}

	public List<Species> findAllOrderByCommonName() {
		return this.speciesRepository.findAllOrderByCommonName();
	}

	public List<Species> findByCommonNameContains(@Param("commonName") String commonName) {
		return this.speciesRepository.findByCommonNameContains(commonName);
	}
}
