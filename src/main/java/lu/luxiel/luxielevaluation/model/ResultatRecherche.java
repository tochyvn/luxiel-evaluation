package lu.luxiel.luxielevaluation.model;


public class ResultatRecherche {

	private String maladieName;
	
	private Integer nbreDeSymptomeCommun;

	public ResultatRecherche(String maladieName, Integer nbreDeSymptomeCommun) {
		this();
		this.maladieName = maladieName;
		this.nbreDeSymptomeCommun = nbreDeSymptomeCommun;
	}

	public ResultatRecherche() {
		super();
	}

	public String getMaladieName() {
		return maladieName;
	}

	public void setMaladieName(String maladieName) {
		this.maladieName = maladieName;
	}

	public Integer getNbreDeSymptomeCommun() {
		return nbreDeSymptomeCommun;
	}

	public void setNbreDeSymptomeCommun(Integer nbreDeSymptomeCommun) {
		this.nbreDeSymptomeCommun = nbreDeSymptomeCommun;
	}
	
	
}
