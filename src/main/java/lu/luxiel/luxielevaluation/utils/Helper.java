package lu.luxiel.luxielevaluation.utils;

import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

	private Helper() {
		throw new IllegalAccessError("Cette classe est un utilitaire, par conséquent ne peut être instanciée");
	}
	
	/**
	 * Générer un entier positif entre min et max
	 * @param min
	 * @param max
	 * @return
	 */
	public static int generateRandomNumber(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max doit être supérieur à min!");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static String convertObjectToJson(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Problème lors de la conversion de l'objet en string json");
		}
	}
}
