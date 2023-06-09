package fr.diginamic.SpeciesREST.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "species")
public class Species {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "common_name", length = 50, nullable = false)
    @NotEmpty
    @Size(max = 50)
	private String commonName;

	@Column(name = "latin_name", length = 120, nullable = false)
    @NotEmpty
    @Size(max = 120)
	private String latinName;

    @JsonIgnore
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

	public Integer getId() {
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