package br.com.empresa.javalanches.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.javalanches.entity.Lanche;
import br.com.empresa.javalanches.repository.LancheRepository;

@RestController
@RequestMapping("/api/v1")
public class LancheController {

	@Autowired
	private LancheRepository lancheRepository;
	
	@GetMapping("/lanches")
	public List<Lanche> buscarLanches() {
		return lancheRepository.findAll();
	}

	@GetMapping("/lanches/{id}")
	public ResponseEntity<Lanche> buscarLanchePorId(@PathVariable(value = "id") Long idLanche) throws Exception {
		Lanche lanche = 
				lancheRepository.findById(idLanche)
					.orElseThrow(() -> new Exception("Lanche não encontrado: " + idLanche));
		//TODO criar uma exception de not found
		
		return ResponseEntity.ok().body(lanche);
	}

	@PostMapping("/lanches")
	public Lanche criarLanche(@RequestBody Lanche lanche) {
		return lancheRepository.save(lanche);
	}

	@PutMapping("/lanches/{id}")
	public ResponseEntity<Lanche> atualizarLanche(
	    @PathVariable(value = "id") Long idLanche, @RequestBody Lanche lancheNovo)
	    throws Exception {
		Lanche lanche = 
				lancheRepository.findById(idLanche)
					.orElseThrow(() -> new Exception("Lanche não encontrado: " + idLanche));
		lanche.setNome(lancheNovo.getNome());

		Lanche lancheAtualizado = lancheRepository.save(lanche);
	    return ResponseEntity.ok(lancheAtualizado);
	}

	@DeleteMapping("/lanches/{id}")
	public Map<String, Boolean> apagarLanche(@PathVariable(value = "id") Long idLanche) throws Exception {
		Lanche lanche = 
				lancheRepository.findById(idLanche)
					.orElseThrow(() -> new Exception("Lanche não encontrado: " + idLanche));
	    lancheRepository.delete(lanche);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}

}
