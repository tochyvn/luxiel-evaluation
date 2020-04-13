package lu.luxiel.luxielevaluation.utils;

import java.util.Random;

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
}
