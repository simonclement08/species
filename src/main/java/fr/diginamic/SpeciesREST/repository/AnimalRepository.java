package fr.diginamic.SpeciesREST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.diginamic.SpeciesREST.enums.Sex;
import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.model.Species;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	/**
	 * Requête qui renvoie tous les animaux de la Species fournie en paramètre
	 * 
	 * @param species
	 */
	List<Animal> findBySpecies(Species species);

	/**
	 * Requête qui renvoie tous les animaux dont la couleur fait partie de la liste de couleurs fournie
	 * 
	 * @param colors Liste de couleurs
	 */
	List<Animal> findByColorIn(List<String> colors);

	/**
	 * Requête qui renvoie le nombre d’Animaux dont le Sex est égal à la valeur donnée en paramètres
	 * 
	 * @param sex
	 */
	@Query("SELECT COUNT(a) FROM Animal a WHERE sex = :sex")
	int countBySex(@Param("sex") Sex sex);

	/**
	 * Requête qui renvoie un booléen si l’animal fourni «appartient» à au moins une personne
	 * 
	 * @param animal
	 */
	@Query("SELECT CASE "
			+ "WHEN (COUNT(a) > 0) THEN true "
			+ "ELSE false END "
			+ "FROM Animal a "
			+ "JOIN a.persons p "
			+ "WHERE a = :animal")
	boolean isOwned(@Param("animal") Animal animal); 
}
