package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Artikl;
import rva.repository.ArtiklRepository;

@Service
public class ArtiklService {

	@Autowired
	private ArtiklRepository artiklRepository;
	
	public List<Artikl> getAllArtikli(){
		return artiklRepository.findAll();
	}

	public Optional<Artikl> getArtiklById(int artiklId) {
		return artiklRepository.findById(artiklId);
	}
	
	public boolean existsById(int id) {
		return getArtiklById(id).isPresent();
	}
	
	public List<Artikl> getAllArtikliByNaziv(String naziv){
		return artiklRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Artikl> getAllArtikliByPocetakNaziva(String pocetakNaziva){
		return artiklRepository.getArtikliByPocetakNaziva(pocetakNaziva.toLowerCase());
	}
	
	public Artikl addArtikl(Artikl artikl) {
		return artiklRepository.save(artikl);
	}
	
	public void deleteById(int id) {
		artiklRepository.deleteById(id);
	}
}
