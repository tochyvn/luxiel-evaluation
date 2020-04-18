package lu.luxiel.luxielevaluation.service;

import java.util.List;
import java.util.Optional;

import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.AlreadyExistEntityException;
import lu.luxiel.luxielevaluation.exception.BadRequestException;
import lu.luxiel.luxielevaluation.exception.SymptomeNotFoundException;

public interface SymptomeService {

	/**
	 * Ajout d'un symptôme
	 * @param symptome
	 * @return
	 */
	Symptome addSymptome(Symptome symptome) throws AlreadyExistEntityException, BadRequestException;
	
	/**
	 * Récupérer un Symptôme par son Id
	 * @param id
	 * @return
	 */
	Symptome getSymptoneById(Integer id) throws SymptomeNotFoundException;
	
	/**
	 * Récupérer tous les Symptomes
	 * @return
	 */
	List<Symptome> getAllSymptomes();
}
