package fr.diginamic.SpeciesREST.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "person")
public class Person {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "age", nullable = true)
    private Integer age;

    @Column(name = "firstname", length = 50, nullable = false)
//    @NotEmpty
//    @Size(max = 50)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false)
    @NotEmpty
    @Size(max = 50)
    private String lastname;

    @ManyToMany
    @JoinTable(
            name = "person_animals",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "animals_id")
    )
    private List<Animal> animals;

	public Person() {
    	this.animals = new ArrayList<>();
    }

    public Person(String firstname, String lastname) {
    	this.animals = new ArrayList<>();
        this.firstname = firstname;
        this.lastname = lastname;
    }

	public Integer getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public void addAnimal(Animal animal) {
        this.animals.add(animal);
        animal.getPersons().add(this);
    }

    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
        animal.getPersons().remove(this);
    }

    @Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + "]";
	}
}
