package lu.luxiel.luxielevaluation.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import lu.luxiel.luxielevaluation.entity.Maladie;
import lu.luxiel.luxielevaluation.entity.Medicament;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;

public interface MedicalService {

	/**
	 * Ajout d'un symptôme
	 * @param symptome
	 * @return
	 */
	Symptome addSymptome(Symptome symptome);
	
	/**
	 * Récupérer un Symptôme par son Id
	 * @param id
	 * @return
	 */
	Optional<Symptome> getSymptoneById(Integer id);
	
	/**
	 * Ajouter une nouvelle maladie
	 * @param maladie
	 * @return
	 */
	Maladie addMaladie(Maladie maladie);
	
	/**
	 * Ajouter un nouveau traitement conccernant une maladie
	 * @param medicament
	 * @return
	 */
	Medicament addMedicament(Medicament medicament);
	
	/**
	 * Récupérer une liste de maladies probantes par rapport à une liste de symptômes
	 * @param symptomes
	 * @return
	 */
	List<ResultatRecherche> findMaladieBySymptome(Set<Symptome> symptomes);
	
	/**
	 * Recupération de toutes les maladies
	 * @return
	 */
	List<Maladie> findAllMaladie();

	/**
	 * Récupérer tous les Symptomes
	 * @return
	 */
	List<Symptome> getAllSymptomes();

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Maladie> findMaladieById(Integer id);
	
}
