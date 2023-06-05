package fr.diginamic.SpeciesMVC.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.diginamic.SpeciesMVC.enums.Sex;
import fr.diginamic.SpeciesMVC.model.Animal;
import fr.diginamic.SpeciesMVC.model.Person;
import fr.diginamic.SpeciesMVC.model.Species;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class PersonRepositoryTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AnimalRepository animalRepository;

	@BeforeEach
	public void initData() {
		em.clear();
		Person p1 = new Person("Clément", "SIMON");
		p1.setAge(23);
		em.persist(p1);
		Person p2 = new Person("Sébastien", "DURANT");
		p2.setAge(53);
		em.persist(p2);
		Person p3 = new Person("Olivier", "Lesage");
		p3.setAge(82);
		Species chat = new Species("Chat", "Felis silvestris catus");
		em.persist(chat);
		Animal garfield = new Animal("Garfield", Sex.M, chat);
		em.persist(garfield);
		p3.addAnimal(garfield);
		em.persist(p3);
		em.flush();
	}

	@Test
	public void findByAgeGreaterThanEqualTest() {
		List<Person> persons = this.personRepository.findByAgeGreaterThanEqual(50);
		Assertions.assertEquals(2, persons.size());
	}

	@Test
	public void findPersonWhereAgeBetweenTest() {
		List<Person> persons = this.personRepository.findPersonWhereAgeBetween(18,50);
		Assertions.assertEquals(1, persons.size());
	}

	@Test
	public void findOwnersOfAnimalTest() {
		List<Animal> garfieldList = animalRepository.findAll();
		Animal garfield = garfieldList.get(0);
		List<Person> persons = this.personRepository.findOwnersOfAnimal(garfield);
		Assertions.assertEquals(1, persons.size());
	}

	@Test
	public void insertRandomPersonsTest() {
		int countBefore = this.personRepository.findAll().size();
		Assertions.assertEquals(3, countBefore);
		this.personRepository.insertRandomPersons(5);
		int countAfter = this.personRepository.findAll().size();
		Assertions.assertEquals(8, countAfter);
	}
	
	@Test
	public void deletePersonsWithoutAnimalTest() {
		this.personRepository.deletePersonsWithoutAnimal();
		int countAfter = this.personRepository.findAll().size();
		Assertions.assertEquals(1, countAfter);
	}
}
