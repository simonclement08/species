package fr.diginamic.SpeciesREST.repository;

public interface PersonRepositoryCustom {
	
	/**
	 * Permet de supprimé toutes les personnes qui n'ont pas d'animaux
	 */
	void deletePersonsWithoutAnimal();
	
	/**
	 * Permet de générer un certains nombre de personnes
	 * 
	 * @param x nombre de personne à générer
	 */
	void insertRandomPersons(int x);
}
