package lu.luxiel.luxielevaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.luxiel.luxielevaluation.entity.Symptome;

@Repository
public interface SymptomeRepository extends JpaRepository<Symptome, Integer> {

	Symptome findByName(String name);
}
