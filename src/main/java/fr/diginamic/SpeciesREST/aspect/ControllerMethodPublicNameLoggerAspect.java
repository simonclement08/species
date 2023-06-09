package fr.diginamic.SpeciesREST.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMethodPublicNameLoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(public * fr.diginamic.SpeciesREST.controller.*.*(..))")
	public void logMethodName(JoinPoint joinPoint) {
		logger.info("Entrée dans la méthode public : {}", joinPoint.getSignature().getName());
	}

}
