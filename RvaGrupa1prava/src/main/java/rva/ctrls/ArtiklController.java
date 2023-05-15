package rva.ctrls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Artikl;
import rva.service.ArtiklService;

@CrossOrigin
@RestController
public class ArtiklController {

	@Autowired
	private ArtiklService artiklService;
	
	/*@GetMapping("/artikl")
	public List<Artikl> getAllArtikli(){
		return artiklService.getAllArtikli();
	}*/
	@GetMapping("/artikl")
	public ResponseEntity<?> getAllArtikli(){
		List<Artikl> artikli = artiklService.getAllArtikli();
		if(artikli.isEmpty())
			return new ResponseEntity<>("Artikli - empty list", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(artikli, HttpStatus.OK);
	}
	
	/*@GetMapping("/artikl/{id}")
	public Optional<Artikl> getArtiklById(@PathVariable("id")int artiklId){
		return artiklService.getArtiklById(artiklId);
	}*/
	@GetMapping("/artikl/{id}")
	public ResponseEntity<?> getArtiklById(@PathVariable("id")int artiklId){
		if(artiklService.existsById(artiklId)) {
			Optional<Artikl> artikl = artiklService.getArtiklById(artiklId);
			return ResponseEntity.ok(artikl);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
		        .body("Artikl by id " + artiklId + " not found");
	}
	
	@GetMapping("/artikl/naziv/{naziv}")
	public ResponseEntity<?> getAllArtikliByNaziv(@PathVariable("naziv")String naziv){
		List<Artikl> artikli = artiklService.getAllArtikliByNaziv(naziv);
		if(artikli.isEmpty())
			return new ResponseEntity<>("Artikli by naziv - empty list", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(artikli, HttpStatus.OK);
	}
	
	@GetMapping("/artikl/pocetakNaziva/{pocetakNaziva}")
	public ResponseEntity<?> getAllArtikliByPocetakNaziva(@PathVariable("pocetakNaziva")String pocetakNaziva){
		List<Artikl> artikli = artiklService.getAllArtikliByPocetakNaziva(pocetakNaziva);
		if(artikli.isEmpty())
			return new ResponseEntity<>("Artikli by naziv - empty list", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(artikli, HttpStatus.OK);
	}
	
	@PostMapping("/artikl")
	public ResponseEntity<?> postArtikl(@RequestBody Artikl artikl){
		if(artiklService.existsById(artikl.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
			        .body("Artikl with id " + artikl.getId() + " already exists");
		}
		Artikl savedArtikl = artiklService.addArtikl(artikl);
		return new ResponseEntity<>(savedArtikl, HttpStatus.OK);
	}
	
	@PutMapping("/artikl/{id}")
	public ResponseEntity<?> putArtikl(@PathVariable("id")int artiklId, 
			@RequestBody Artikl artikl){
		artikl.setId(artiklId);
		if(!artiklService.existsById(artikl.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        .body("Artikl with id " + artikl.getId() + " not found");
		}		
		Artikl updatedArtikl = artiklService.addArtikl(artikl);
		return new ResponseEntity<>(updatedArtikl, HttpStatus.OK);
	}
	
	@DeleteMapping("/artikl/{id}")
	public ResponseEntity<?> deleteArtikl(@PathVariable("id")int artiklId){
		if(!artiklService.existsById(artiklId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			        .body("Artikl with id " + artiklId + " not found");
		}
		artiklService.deleteById(artiklId);
		return new ResponseEntity<>("Artikl with id " + artiklId + " has been deleted", HttpStatus.OK);
	}
	
	
	
	
}
