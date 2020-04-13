package lu.luxiel.luxielevaluation.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
public class Maladie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
        name = "Maladie_Symptome", 
        joinColumns = { @JoinColumn(name = "maladie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "symptome_id") }
    )
	private Set<Symptome> symptomes;
	
	public Maladie() {
		super();
	}
	
	public Maladie(Set<Symptome> symptomes) {
		this.symptomes = symptomes;
	}
	
	/***
	 * 
	 * @param symptomes
	 * @return
	 */
	public Maladie addSymptome(Symptome symptome) {
		if (this.symptomes == null) {
			this.symptomes = new HashSet<>();
		}
		this.symptomes.add(symptome);
		
		return this;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Symptome> getSymptomes() {
		return symptomes;
	}

	public void setSymptomes(Set<Symptome> symptomes) {
		this.symptomes = symptomes;
	}

}
