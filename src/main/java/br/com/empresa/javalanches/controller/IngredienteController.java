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

import br.com.empresa.javalanches.entity.Ingrediente;
import br.com.empresa.javalanches.repository.IngredienteRepository;

@RestController
@RequestMapping("/api/v1")
public class IngredienteController {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@GetMapping("/ingredientes")
	public List<Ingrediente> buscarIngredientes() {
		return ingredienteRepository.findAll();
	}

	@GetMapping("/ingredientes/{id}")
	public ResponseEntity<Ingrediente> buscarIngredientePorId(@PathVariable(value = "id") Long idIngrediente) throws Exception {
		Ingrediente ingrediente = 
				ingredienteRepository.findById(idIngrediente)
					.orElseThrow(() -> new Exception("Ingrediente não encontrado: " + idIngrediente));
		//TODO criar uma exception de not found
		
		return ResponseEntity.ok().body(ingrediente);
	}

	@PostMapping("/ingredientes")
	public Ingrediente criarIngrediente(@RequestBody Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}

	@PutMapping("/ingredientes/{id}")
	public ResponseEntity<Ingrediente> atualizarIngrediente(
	    @PathVariable(value = "id") Long idIngrediente, @RequestBody Ingrediente ingredienteNovo)
	    throws Exception {
		Ingrediente ingrediente = 
				ingredienteRepository.findById(idIngrediente)
					.orElseThrow(() -> new Exception("Ingrediente não encontrado: " + idIngrediente));
		ingrediente.setNome(ingredienteNovo.getNome());
		ingrediente.setPreco(ingredienteNovo.getPreco());

		Ingrediente ingredienteAtualizado = ingredienteRepository.save(ingrediente);
	    return ResponseEntity.ok(ingredienteAtualizado);
	}

	@DeleteMapping("/ingredientes/{id}")
	public Map<String, Boolean> apagarIngrediente(@PathVariable(value = "id") Long idIngrediente) throws Exception {
		Ingrediente ingrediente = 
				ingredienteRepository.findById(idIngrediente)
					.orElseThrow(() -> new Exception("Ingrediente não encontrado: " + idIngrediente));
	    ingredienteRepository.delete(ingrediente);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}

}
