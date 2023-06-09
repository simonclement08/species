package fr.diginamic.SpeciesREST.mapper;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.SpeciesREST.dto.PersonDto;
import fr.diginamic.SpeciesREST.model.Animal;
import fr.diginamic.SpeciesREST.model.Person;

public class PersonMapper {
	
	public static List<PersonDto> toDtos(List<Person> persons){
		List<PersonDto> personDtos = new ArrayList<PersonDto>();
		for (Person person : persons) {
			personDtos.add(PersonMapper.toDto(person));
		}
		return personDtos;
	}

	public static PersonDto toDto(Person person) {
		PersonDto personDto = new PersonDto();
		personDto.setId(person.getId());
		personDto.setNomAge(person.getLastname() + " " + person.getFirstname() + " (" + person.getAge() + ")");
		personDto.setAnimaux(setAnimaux(person.getAnimals()));
		return personDto;
	}

	public static String[] setAnimaux(List<Animal> animals) {
		String[] animaux = new String[animals.size()];

		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			animaux[i] = animal.getName() + " (" + animal.getSpecies().getCommonName() + ")";
		}

		return animaux;
	}
}
