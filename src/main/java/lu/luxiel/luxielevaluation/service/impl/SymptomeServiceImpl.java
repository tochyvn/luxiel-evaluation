package lu.luxiel.luxielevaluation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.AlreadyExistEntityException;
import lu.luxiel.luxielevaluation.exception.BadRequestException;
import lu.luxiel.luxielevaluation.exception.SymptomeNotFoundException;
import lu.luxiel.luxielevaluation.repository.SymptomeRepository;
import lu.luxiel.luxielevaluation.service.SymptomeService;

@Service
public class SymptomeServiceImpl implements SymptomeService {

	@Autowired
	private SymptomeRepository symptomeRepository;

	@Override
	public List<Symptome> getAllSymptomes() {
		return symptomeRepository.findAll();
	}

	@Override
	public Symptome addSymptome(Symptome symptome) throws AlreadyExistEntityException, BadRequestException {
		if (symptome == null || symptome.getName() == null || "".equals(symptome.getName().trim())) {
			throw new BadRequestException("Les paramètres dans la requête d'ajout d'un Symptôme sont incorrecte");
		}
		
		if (symptomeRepository.findByName(symptome.getName()) != null) {
			throw new AlreadyExistEntityException("Un symptome ayant pour nom : " + symptome.getName() + " existe déjà!");
		}
		
		return symptomeRepository.save(symptome);
	}

	@Override
	public Symptome getSymptoneById(Integer id) throws SymptomeNotFoundException {
		return symptomeRepository.findById(id).orElseThrow(() -> new SymptomeNotFoundException("Le symptôme ayant pour id : " + id + " est introuvable"));
	}

}
