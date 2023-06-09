package fr.diginamic.SpeciesREST.dto;

public class PersonDto {
	private Integer id;
	private String nomAge;
	private String[] animaux;

	public PersonDto(Integer id, String nomAge, String[] animaux) {
		super();
		this.id = id;
		this.nomAge = nomAge;
		this.animaux = animaux;
	}
	
	public PersonDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomAge() {
		return nomAge;
	}

	public void setNomAge(String nomAge) {
		this.nomAge = nomAge;
	}

	public String[] getAnimaux() {
		return animaux;
	}

	public void setAnimaux(String[] animaux) {
		this.animaux = animaux;
	}

}
