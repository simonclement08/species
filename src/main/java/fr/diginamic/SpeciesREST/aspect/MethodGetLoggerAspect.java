package fr.diginamic.SpeciesREST.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodGetLoggerAspect {
	
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* fr.diginamic.SpeciesREST..get*(..))")
    public void logGetMethod(JoinPoint joinPoint) {
		logger.info("Une méthode get a été appelée : {}", joinPoint.getSignature().getName());
    }

}
