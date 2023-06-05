package fr.diginamic.springbootdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.springbootdemo.enums.Sex;
import fr.diginamic.springbootdemo.model.Animal;
import fr.diginamic.springbootdemo.model.Species;
import fr.diginamic.springbootdemo.repository.AnimalRepository;
import fr.diginamic.springbootdemo.repository.PersonRepository;
import fr.diginamic.springbootdemo.repository.SpeciesRepository;
import fr.diginamic.springbootdemo.service.BavardService;
import jakarta.transaction.Transactional;

@SpringBootApplication
@RestController
public class SpringBootDemoApplication implements CommandLineRunner {

	@Autowired
	private BavardService bavardService;

	@Autowired
	private SpeciesRepository speciesRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AnimalRepository animalRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		bavardService.parler();
		return "Hello, world!";
	}

	@GetMapping("/blabla")
	public String blabla() {
		return bavardService.parler();
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Test du repository Species
		System.out.println("Liste des espèces : " + speciesRepository.findAll());

		Species species = new Species("Lion", "Panthera leo");
		speciesRepository.save(species);
		System.out.println("Espèce sauvegardée: " + species);

		Optional<Species> oneSpecies = speciesRepository.findById(3);
		Species rabit = oneSpecies.orElse(new Species());
		System.out.println("Espece 3 : " + rabit);

		int countBefore = speciesRepository.findAll().size();
		speciesRepository.delete(species);
		int countAfter = speciesRepository.findAll().size();
		System.out.println("Une espèce a été supprimée, avant il y avait " + countBefore
				+ " espèces, maintenant il y en a " + countAfter);

		Species cat = speciesRepository.findFirstByCommonName("Chat");
		System.out.println("L'espèces dont le nom commun est chat : " + cat);

		List<Species> speciesLU = speciesRepository.findByLatinNameContainsIgnoreCase("LU");
		System.out.println("Liste des espèces dont le nom lation comtient LU : " + speciesLU);

		System.out.println("Liste des espèces ordonnés par leur nom commun : ");
		System.out.println(speciesRepository.findAllOrderByCommonName());

		System.out.println("Liste des espèces dont leur nom contient Ch : ");
		System.out.println(speciesRepository.findByCommonNameContains("ch"));

		// Test du repository Person
		System.out.println("Liste des Personnes qui s'appellent John ou leur nom est Lamarque : ");
		System.out.println(personRepository.findByLastnameOrFirstname("Lamarque", "John"));

		System.out.println("Liste des personnes qui ont plus de 50 ans : ");
		System.out.println(personRepository.findByAgeGreaterThanEqual(50));

		System.out.println("Liste des personnes qui ont plus de 30 ans et moins de 50 : ");
		System.out.println(personRepository.findPersonWhereAgeBetween(30, 50));

		Optional<Animal> maxOpt = animalRepository.findById(1);
		Animal max = maxOpt.orElseThrow();
		System.out.println("Liste des personnes qui possèdent Max : ");
		System.out.println(personRepository.findOwnersOfAnimal(max));
		
		int countPersonBefore = personRepository.findAll().size();
		personRepository.insertRandomPersons(5);
		int countPersonAfter = personRepository.findAll().size();
		System.out.println("Les 5 personnes ont bien été générées, avant il y avait " + countPersonBefore
				+ " personnes dans la base, maintenant il y en a " + countPersonAfter);
		
		personRepository.deletePersonsWithoutAnimal();
		int countPersonAfterDelete = personRepository.findAll().size();
		System.out.println("Les personnes sans animaux ont bien été supprimées, avant il y avait " + countPersonAfter
				+ " personnes dans la base, maintenant il y en a " + countPersonAfterDelete);
		
		// Test du repository Animal
		Optional<Species> cats = speciesRepository.findById(1);
		Species catSpecies = cats.orElseThrow();
		System.out.println("Liste des chats : ");
		System.out.println(animalRepository.findBySpecies(catSpecies));

		List<String> colors = new ArrayList<String>();
		colors.add("Blanc");
		colors.add("Noir");
		System.out.println("Liste des animaux noirs et blancs : ");
		System.out.println(animalRepository.findByColorIn(colors));

		System.out.println("Nombre d'animaux du sexe feminin : " + animalRepository.countBySex(Sex.F));

		boolean isOwned = animalRepository.isOwned(max);
		System.out.println("Max " + (isOwned ? "appartient à au moins une" : "n'appartient à") + " personne.");
		Optional<Species> dogs = speciesRepository.findById(2);
		Species dogsSpecies = dogs.orElseThrow();
		Animal belle = new Animal("Belle", Sex.F, dogsSpecies);
		animalRepository.save(belle);
		boolean isOwned2 = animalRepository.isOwned(belle);
		System.out.println("Belle " + (isOwned2 ? "appartient à au moins une" : "n'appartient à") + " personne.");
	}
}
