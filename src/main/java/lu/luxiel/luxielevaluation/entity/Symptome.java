package lu.luxiel.luxielevaluation.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Symptome {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	
	public Symptome() {
		super();
	}
	
	public Symptome(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
        if( ! (obj instanceof Symptome) ) return false;

        Symptome other = (Symptome) obj;

        return new EqualsBuilder().append(this.name, other.name).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(name).toHashCode();
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

	@Override
	public String toString() {
		return "Symptome [id=" + id + ", name=" + name + "]";
	}
	
}
