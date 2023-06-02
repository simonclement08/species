package fr.diginamic.SpeciesREST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.SpeciesREST.model.Species;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
	
	/**
	 * Retourne la première Species dont le nom commun est égal au paramètre fourni
	 * 
	 * @param commonName nom commun
	 */
	Species findFirstByCommonName(String commonName);

	/**
	 * Retourne une liste de Species dont le nom latin contientle paramètre fournien ignorant la casse
	 * 
	 * @param latinName nom latin
	 */
	List<Species> findByLatinNameContainsIgnoreCase(String latinName);

	/**
	 * Retourne une liste de Species ordonnées par nom commun ascendant
	 * 
	 */
	@Query("FROM Species ORDER BY commonName")
	List<Species> findAllOrderByCommonName();

	/**
	 * Retourne une liste de Species avec un nom commun incluant le paramètre fourni
	 * 
	 * @param commonName
	 */
	@Query("FROM Species WHERE commonName LIKE CONCAT('%', :commonName, '%')")
	List<Species> findByCommonNameContains(@Param("commonName") String commonName);
}