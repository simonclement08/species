package fr.diginamic.SpeciesMVC.model;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.SpeciesMVC.enums.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "animal")
public class Animal {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "color", length = 50, nullable = true)
    @Size(max = 50)
    private String color;
    
    @Column(name = "name", length = 50, nullable = false)
    @NotEmpty
    @Size(max = 50)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 255, nullable = false)
    private Sex sex;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    @NotEmpty
    private Species species;

    @ManyToMany(mappedBy = "animals")
    private List<Person> persons;

    // Constructeurs

    public Animal() {
    	this.persons = new ArrayList<>();
    }

	public Animal(String name, Sex sex, Species species) {
		super();
    	this.persons = new ArrayList<>();
		this.name = name;
		this.sex = sex;
		this.species = species;
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person) {
        this.persons.add(person);
        person.getAnimals().add(this);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
        person.getAnimals().remove(this);
    }

	@Override
	public String toString() {
		return "Animal [name=" + name + ", sex=" + sex + ", species=" + species + "]";
	}

}
