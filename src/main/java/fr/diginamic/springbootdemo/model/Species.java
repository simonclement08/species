package fr.diginamic.springbootdemo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "species")
public class Species {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "common_name", length = 50, nullable = false)
	private String commonName;

	@Column(name = "latin_name", length = 50, nullable = false)
	private String latinName;

	@OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private List<Animal> animals;

	// Constructeur
	public Species() {
	}

	public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
    }

	// Getters et setters

	public int getId() {
		return id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	@Override
	public String toString() {
		return "Species [commonName=" + commonName + ", latinName=" + latinName + "]";
	}

}