package lu.luxiel.luxielevaluation.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lu.luxiel.luxielevaluation.entity.Maladie;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.AlreadyExistEntityException;
import lu.luxiel.luxielevaluation.exception.BadRequestException;
import lu.luxiel.luxielevaluation.exception.InitializationRuntimeException;
import lu.luxiel.luxielevaluation.exception.SymptomeNotFoundException;
import lu.luxiel.luxielevaluation.service.MaladieService;
import lu.luxiel.luxielevaluation.service.SymptomeService;
import lu.luxiel.luxielevaluation.utils.Helper;

@Component
public class DataInitializer {
	
	private static final int NOMBRE_DE_SYMPTOME = 5;
	
	private static final int NOMBRE_MALADIES = 7;
	
	@Autowired
	private MaladieService maladieService;
	
	@Autowired
	private SymptomeService symptomeService;
	
	List<Symptome> symptomes;
	
	List<Maladie> maladies;

	public void initializeData() {
		this.symptomes = new ArrayList<>();
		initializeSymptomes();
		initializeMaladies();
	}
	
	private void initializeSymptomes() {
		List<Symptome> symptomes = Arrays.asList(
				new Symptome("Céphalée"),
				new Symptome("Douleur yeux"),
				new Symptome("Pulsation"),
				new Symptome("Nausées"),
				new Symptome("Vomissements"),
				new Symptome("Sensibilité lumière"),
				new Symptome("Sensibilité bruit"),
				new Symptome("Fatigue"),
				new Symptome("Deglutition douloureuse"),
				new Symptome("Maux de gorge"),
				new Symptome("Fievre"),
				new Symptome("Congestion nasale"),
				new Symptome("Toux"),
				new Symptome("Hémoptysie"),
				new Symptome("Souffle court"),
				new Symptome("Dyspnée"),
				new Symptome("Douleur toracique"),
				new Symptome("Respiration sifflante"),
				new Symptome("Oppression thoracique"),
				new Symptome("Sueur"),
				new Symptome("Vertiges"),
				new Symptome("Nervosité"),
				new Symptome("Palpitations"),
				new Symptome("Pâleur")
				);
		symptomes.forEach(symptome -> {
			try {
				symptomeService.addSymptome(symptome);
			} catch (AlreadyExistEntityException | BadRequestException e) {
				throw new InitializationRuntimeException(e);
			}
			this.symptomes.add(symptome);
		});
	}
	
	/**
	 * Initialisation de 7 Maladies
	 */
	private void initializeMaladies()
	{
		for(int i = 0; i <= NOMBRE_MALADIES; i++) {
			Maladie maladie = new Maladie();
			maladie.setName("Maladie_" + i);
			generateRandomSymptoms().forEach(id -> {
				Symptome symptome;
				try {
					symptome = symptomeService.getSymptoneById(id);
					maladie.addSymptome(symptome);
				} catch (SymptomeNotFoundException e) {
					throw new InitializationRuntimeException(e);
				}
			});
			maladieService.addMaladie(maladie);
		}
	}
	
	/**
	 * Generation d'une liste aléatoire des Ids de 5 symptômes
	 * @return
	 */
	private Set<Integer> generateRandomSymptoms() {
		Set<Integer> ids = new HashSet<>();
		while(ids.size() != NOMBRE_DE_SYMPTOME) {
			int randomIndex = Helper.generateRandomNumber(0, this.symptomes.size() - 1);
			System.out.println("Index " + randomIndex + " nbre : " + (this.symptomes.size() - 1));
			ids.add(this.symptomes.get(randomIndex).getId());
			System.out.println("Size : " + symptomes.size());
		}
		return ids;
	}
}
