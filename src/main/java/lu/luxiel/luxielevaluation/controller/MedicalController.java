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
import org.springframework.web.server.ResponseStatusException;

import lu.luxiel.luxielevaluation.entity.Maladie;
import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.EntityNotFoundException;
import lu.luxiel.luxielevaluation.model.ResultatRecherche;
import lu.luxiel.luxielevaluation.service.MedicalService;

@RestController
@RequestMapping("/")
public class MedicalController {
	
	@Autowired
	private MedicalService medicalService;

	@GetMapping("/home")
	public String test() {
		return "test";
	}
	
	@GetMapping("/")
	public String home() {
		return "bienvenue sur le Webservice Rest du corps Medical";
	}
	
	@GetMapping("/symptome")
	public ResponseEntity<List<Symptome>> getSymptomes() {
		List<Symptome> symptomes = medicalService.getAllSymptomes();
		return new ResponseEntity<List<Symptome>>(symptomes, HttpStatus.OK);
	}
	
	@GetMapping("/symptome/{id}")
	public ResponseEntity<Symptome> getSymptomeById(@PathVariable Integer id) {
		Symptome symptome = null;
		return new ResponseEntity<Symptome>(symptome, HttpStatus.OK);
	}
	
	@PostMapping("/symptome")
	public ResponseEntity<Void> addSymptome(@RequestBody Symptome symptome) {
		medicalService.addSymptome(symptome);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/maladie")
	public ResponseEntity<List<Maladie>> getAllMaladies() {
		List<Maladie> maladies = medicalService.findAllMaladie();
		return new ResponseEntity<>(maladies, HttpStatus.OK);
	}
	
	@GetMapping("/maladie/{id}")
	public ResponseEntity<Maladie> getMaladieById(@PathVariable int id) {
		Maladie maladie;
		try {
			maladie = medicalService.findMaladieById(id).orElseThrow(() -> new EntityNotFoundException("Maladie introuvable"));
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		
		return new ResponseEntity<>(maladie, HttpStatus.OK);
	}
	
	@PostMapping("/find")
	public ResponseEntity<List<ResultatRecherche>> findMaladieBySymptome(@RequestBody Set<Symptome> symptomes) {
		List<ResultatRecherche> maladies = medicalService.findMaladieBySymptome(symptomes);
		return new ResponseEntity<>(maladies, HttpStatus.OK);
	}
	
}
