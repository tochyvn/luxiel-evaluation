package lu.luxiel.luxielevaluation.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lu.luxiel.luxielevaluation.entity.Maladie;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.MaladieNotFoundException;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;
import lu.luxiel.luxielevaluation.service.MaladieService;

@RestController
@RequestMapping("/api/v1")
public class MaladieController {

	@Autowired
	private MaladieService maladieService;

	@GetMapping("/maladie")
	public ResponseEntity<List<Maladie>> getAllMaladies() {
		List<Maladie> maladies = maladieService.findAllMaladie();
		return new ResponseEntity<>(maladies, HttpStatus.OK);
	}

	@GetMapping("/maladie/{id}")
	public ResponseEntity<Maladie> getMaladieById(@PathVariable int id) throws MaladieNotFoundException {
		Maladie maladie = maladieService.findMaladieById(id).orElseThrow(() -> new MaladieNotFoundException("Maladie introuvable d'id " + id + " est introuvable"));

		return new ResponseEntity<>(maladie, HttpStatus.OK);
	}

	@PostMapping("/maladie")
	public ResponseEntity<Maladie> addMaladie(@RequestBody Maladie maladie) {
		Maladie added = maladieService.addMaladie(maladie);
		return new ResponseEntity<>(added, HttpStatus.CREATED);
	}

	@PostMapping("/maladie/symptome")
	public ResponseEntity<List<ResultatRecherche>> findMaladieBySymptome(@RequestBody Set<Symptome> symptomes) throws MaladieNotFoundException {
		List<ResultatRecherche> maladies = maladieService.findMaladieBySymptome(symptomes);
		
		return new ResponseEntity<>(maladies, HttpStatus.OK);
	}

}
