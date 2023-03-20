package rva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Dobavljac;
import rva.model.Porudzbina;
import rva.model.StavkaPorudzbine;


public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer>{

}
