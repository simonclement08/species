package fr.diginamic.SpeciesREST.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronService {

	@Scheduled(cron = "${cron.everyminute}" )
	public void executeEveryMinute() {
		System.out.println("La méthode s'exécute toutes les minutes.");
	}
}
