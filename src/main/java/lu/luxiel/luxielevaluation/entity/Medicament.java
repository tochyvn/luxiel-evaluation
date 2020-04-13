package lu.luxiel.luxielevaluation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Medicament {

	@Id
	private Integer id;
	
	private String name;
	
}
