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
import lu.luxiel.luxielevaluation.entity.Medicament;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;
import lu.luxiel.luxielevaluation.repository.MaladieRepository;
import lu.luxiel.luxielevaluation.repository.MedicamentRepository;
import lu.luxiel.luxielevaluation.repository.SymptomeRepository;
import lu.luxiel.luxielevaluation.service.MedicalService;

@Service
public class MedicalServiceImpl implements MedicalService {

	@Autowired
	private MedicamentRepository medicamentRepository;

	@Autowired
	private MaladieRepository maladieRepository;

	@Autowired
	private SymptomeRepository symptomeRepository;

	@Override
	public Symptome addSymptome(Symptome symptome) {

		return symptomeRepository.save(symptome);
	}

	@Override
	public Medicament addMedicament(Medicament medicament) {
		return medicamentRepository.save(medicament);
	}

	@Override
	public Optional<Symptome> getSymptoneById(Integer id) {
		return symptomeRepository.findById(id);
	}

	@Override
	public Maladie addMaladie(Maladie maladie) {
		return maladieRepository.save(maladie);
	}

	@Override
	public List<ResultatRecherche> findMaladieBySymptome(Set<Symptome> researchList) {
		Set<String> researchSymptomes = researchList.stream().map(Symptome::getName).collect(Collectors.toSet());
		System.out.println("============> Symptômes recherchés");
		System.out.println(researchSymptomes);
		List<Maladie> maladies = maladieRepository.findAll();

		List<ResultatRecherche> resultats = new ArrayList<ResultatRecherche>();

		// Pour chaque maladie
		maladies.forEach(maladie -> {
			System.out.println("======================= Maladie : " + maladie.getName() + " =====================");
			Set<String> symptomes = maladie.getSymptomes().stream().map(Symptome::getName).collect(Collectors.toSet());
			System.out.println(symptomes);
			if (!Collections.disjoint(symptomes, researchSymptomes)) {
				System.out.println("Ensemble pas disjoint");
				// On récupère les Symptômes en commun
				Set<String> commonsSymptoms = researchSymptomes.stream().filter(symptomes::contains)
						.collect(Collectors.toSet());

				ResultatRecherche resultat = new ResultatRecherche(maladie.getName(), commonsSymptoms.size());
				resultats.add(resultat);
			}

		});

		System.out.println("\n\nResultats avant tri: " + resultats);
		resultats.forEach(r -> System.out.println(r.getMaladieName() + " ===> " + r.getNbreDeSymptomeCommun()));


		System.out.println("\n\nResultats avant tri: " + resultats);
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

	@Override
	public List<Symptome> getAllSymptomes() {
		return symptomeRepository.findAll();
	}

}
