package lu.luxiel.luxielevaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lu.luxiel.luxielevaluation.component.DataInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LuxielEvaluationApplication implements CommandLineRunner {
	
	@Autowired
	private DataInitializer initializer;

	public static void main(String[] args) {
		SpringApplication.run(LuxielEvaluationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Initialisation des données");
		initializer.initializeData();		
	}

}
