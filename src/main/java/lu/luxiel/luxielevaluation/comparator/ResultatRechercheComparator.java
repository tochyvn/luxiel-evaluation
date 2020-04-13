package lu.luxiel.luxielevaluation.comparator;

import java.util.Comparator;

import lu.luxiel.luxielevaluation.model.ResultatRecherche;

/**
 * Comparateur permettant de trier les résultats de la recherche
 * @author tochl
 *
 */
public class ResultatRechercheComparator implements Comparator<ResultatRecherche> {

	@Override
	public int compare(ResultatRecherche result1, ResultatRecherche result2) {
		return result2.getNbreDeSymptomeCommun().compareTo(result1.getNbreDeSymptomeCommun());
	}

}
