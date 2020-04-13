package lu.luxiel.luxielevaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.luxiel.luxielevaluation.entity.Medicament;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {

}
