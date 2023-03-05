package rs.ac.uns.ftn.informatika.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.ac.uns.ftn.informatika.aop.AopExampleApplication;
import rs.ac.uns.ftn.informatika.aop.service.SampleService;

// Aspekti (engl. Aspects) su koncepti u programiranju koji se koriste za razdvajanje funkcionalnosti koja se može primeniti na više delova koda. 
// Aspektno orijentisano programiranje (engl. Aspect-oriented programming - AOP) je paradigma programiranja koja koristi ove koncepte, 
// kako bi se povećala modularnost i olakšalo održavanje koda.

// U Spring Boot-u, AOP nam omogućava da definišemo aspekte koji se primenjuju na različite delove aplikacije, kao što su pozivi metode, upiti u bazu podataka i sl.
// AOP se može primeniti i na transakcije, proveru autorizacije, logging, i validacija.

@SpringBootApplication
public class AopExampleApplication implements CommandLineRunner {
	
	/* da bismo testirali aspekte,
	 * direktno smo pozvali u glavnoj klasi metodu,
	 * inace bi pozivi isli npr. u nekom kontroleru
	 */
	@Autowired
	private SampleService sampleService;

	@Override
	public void run(String... args) {
		this.sampleService.someMethodReturning();
		this.sampleService.someMethodAround();
		this.sampleService.someMethodBefore("neki string");
	}

	public static void main(String[] args) {
		
		SpringApplication.run(AopExampleApplication.class, args);
	}
}
