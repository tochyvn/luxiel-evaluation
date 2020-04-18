package lu.luxiel.luxielevaluation.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lu.luxiel.luxielevaluation.entity.Symptome;
import lu.luxiel.luxielevaluation.exception.AlreadyExistEntityException;
import lu.luxiel.luxielevaluation.exception.BadRequestException;
import lu.luxiel.luxielevaluation.exception.SymptomeNotFoundException;
import lu.luxiel.luxielevaluation.service.SymptomeService;

@RestController
@RequestMapping("/api/v1")
public class SymptomeController {
	
	@Autowired
	private SymptomeService symptomeService;

	@GetMapping("/symptome")
	public ResponseEntity<List<Symptome>> getSymptomes() {
		List<Symptome> symptomes = symptomeService.getAllSymptomes();
		return new ResponseEntity<List<Symptome>>(symptomes, HttpStatus.OK);
	}
	
	@GetMapping("/symptome/{id}")
	public ResponseEntity<Symptome> getSymptomeById(@PathVariable Integer id) throws SymptomeNotFoundException {
		Symptome symptome = symptomeService.getSymptoneById(id);

		return new ResponseEntity<Symptome>(symptome, HttpStatus.OK);
	}
	
	@PostMapping("/symptome")
	public ResponseEntity<Symptome> addSymptome(@RequestBody Symptome symptome) throws AlreadyExistEntityException, BadRequestException {
		Symptome saved = symptomeService.addSymptome(symptome);
		
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
}
