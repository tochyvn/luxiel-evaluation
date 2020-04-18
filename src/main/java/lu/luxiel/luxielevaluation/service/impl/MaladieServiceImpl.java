package lu.luxiel.luxielevaluation.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lu.luxiel.luxielevaluation.comparator.ResultatRechercheComparator;
import lu.luxiel.luxielevaluation.entity.Maladie;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.MaladieNotFoundException;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;
import lu.luxiel.luxielevaluation.repository.MaladieRepository;
import lu.luxiel.luxielevaluation.service.MaladieService;

@Service
public class MaladieServiceImpl implements MaladieService {

	@Autowired
	private MaladieRepository maladieRepository;

	@Override
	public Maladie addMaladie(Maladie maladie) {
		return maladieRepository.save(maladie);
	}

	@Override
	public List<ResultatRecherche> findMaladieBySymptome(Set<Symptome> researchList) throws MaladieNotFoundException {
		Set<String> researchSymptomes = researchList.stream().map(Symptome::getName).collect(Collectors.toSet());
		List<Maladie> maladies = maladieRepository.findAll();
		List<ResultatRecherche> resultats = new ArrayList<ResultatRecherche>();

		// Pour chaque maladie
		maladies.forEach(maladie -> {
			Set<String> symptomes = maladie.getSymptomes().stream().map(Symptome::getName).collect(Collectors.toSet());
			if (!Collections.disjoint(symptomes, researchSymptomes)) {
				System.out.println("Ensemble pas disjoint");
				// On récupère les Symptômes en commun
				Set<String> commonsSymptoms = researchSymptomes.stream().filter(symptomes::contains)
						.collect(Collectors.toSet());

				ResultatRecherche resultat = new ResultatRecherche(maladie.getName(), commonsSymptoms.size());
				resultats.add(resultat);
			}

		});
		
		if (maladies.isEmpty()) {
			throw new MaladieNotFoundException("Aucune maladie ne correspond aux symptômes recherchés");
		}

		resultats.forEach(r -> System.out.println(r.getMaladieName() + " ===> " + r.getNbreDeSymptomeCommun()));
		resultats.sort(new ResultatRechercheComparator());
		resultats.forEach(r -> System.out.println(r.getMaladieName() + " ===> " + r.getNbreDeSymptomeCommun()));

		return resultats;
	}

	@Override
	public List<Maladie> findAllMaladie() {
		return maladieRepository.findAll();
	}
	
	@Override
	public Optional<Maladie> findMaladieById(Integer id) {
		return maladieRepository.findById(id);
	}

	

}
