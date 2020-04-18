package lu.luxiel.luxielevaluation.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import lu.luxiel.luxielevaluation.entity.Maladie;
import lu.luxiel.luxielevaluation.entity.Medicament;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.MaladieNotFoundException;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;

public interface MaladieService {
	
	/**
	 * Ajouter une nouvelle maladie
	 * @param maladie
	 * @return
	 */
	Maladie addMaladie(Maladie maladie);
	
	/**
	 * Récupérer une liste de maladies probantes par rapport à une liste de symptômes
	 * @param symptomes
	 * @return
	 */
	List<ResultatRecherche> findMaladieBySymptome(Set<Symptome> symptomes) throws MaladieNotFoundException;
	
	/**
	 * Recupération de toutes les maladies
	 * @return
	 */
	List<Maladie> findAllMaladie();
	

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Maladie> findMaladieById(Integer id);
	
}
