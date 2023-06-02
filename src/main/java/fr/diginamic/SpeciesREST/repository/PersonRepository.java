package fr.diginamic.SpeciesREST.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {

	/**
	 * Requête qui retourne les personnes ayant pour nom le premier paramètre fourni ou ayant pour prénom le second paramètre fourni
	 * 
	 * @param lastname nom
	 * @param firstname prénom
	 */
	List<Person> findByLastnameOrFirstname(String lastname, String firstname);

	/**
	 * Requête qui retourne toutes les personnes d’un âge supérieur ou égal au paramètre fourni
	 * @param age
	 */
	List<Person> findByAgeGreaterThanEqual(Integer age);

	/**
	 * Requête qui retourne toutes les personnes dont l’âge est entre «age min» et «age max»
	 * 
	 * @param min Age min
	 * @param max Age max
	 */
	@Query("FROM Person WHERE age >= :min AND age <= :max")
	List<Person> findPersonWhereAgeBetween(@Param("min") int min, @Param("max") int max);

	/**
	 * Requête qui retourne toutes les personnes qui possèdent l’animal donné en paramètre
	 * 
	 * @param animal
	 */
	@Query("FROM Person p JOIN p.animals a WHERE a = :animal")
	List<Person> findOwnersOfAnimal(@Param("animal") Animal animal);
}