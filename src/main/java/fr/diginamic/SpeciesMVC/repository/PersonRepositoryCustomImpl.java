package fr.diginamic.SpeciesMVC.repository;

import java.util.Locale;

import com.github.javafaker.Faker;

import fr.diginamic.SpeciesMVC.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void deletePersonsWithoutAnimal() {
		String sql = "DELETE FROM person WHERE id NOT IN(SELECT person_id FROM person_animals)";
		em.createNativeQuery(sql).executeUpdate();
	}

	@Override
	public void insertRandomPersons(int x) {
		Faker faker = new Faker(Locale.FRANCE);
		System.out.println("Personnes générées :");
		for (int i = 0; i < x; i++) {
			Person person = new Person(faker.name().firstName(), faker.name().lastName());
			System.out.println("- " + person);
			em.persist(person);
		}
	}

}
