package lu.luxiel.luxielevaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.luxiel.luxielevaluation.entity.Maladie;

@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Integer>{

}
