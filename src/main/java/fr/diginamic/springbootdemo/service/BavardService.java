package fr.diginamic.springbootdemo.service;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BavardService {
	private String nom = "Clément";

	@PostConstruct
	private void postConstruct() {
		System.out.println("Bonjour, ceci est le postConstruct !");
	}

	public String parler() {
		return getNom() + " de la classe " + this.getClass().getSimpleName();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
