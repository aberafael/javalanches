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

import br.com.empresa.javalanches.entity.LanchePedido;
import br.com.empresa.javalanches.repository.LanchePedidoRepository;
import br.com.empresa.javalanches.service.PedidoService;

@RestController
@RequestMapping("/api/v1")
public class LanchePedidoController {

	@Autowired
	private LanchePedidoRepository lancheRepository;

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/lanchesPedido")
	public List<LanchePedido> buscarLanchePedidos() {
		return lancheRepository.findAll();
	}

	@GetMapping("/lanchesPedido/{id}")
	public ResponseEntity<LanchePedido> buscarLanchePedidoPorId(@PathVariable(value = "id") Long idLanchePedido) throws Exception {
		LanchePedido lanche = 
				lancheRepository.findById(idLanchePedido)
					.orElseThrow(() -> new Exception("LanchePedido não encontrado: " + idLanchePedido));
		//TODO criar uma exception de not found
		
		return ResponseEntity.ok().body(lanche);
	}

	@PostMapping("/lanchesPedido")
	public LanchePedido criarLanchePedido(@RequestBody LanchePedido lanche) {
		return lancheRepository.save(lanche);
	}

	@PutMapping("/lanchesPedido/{id}")
	public ResponseEntity<LanchePedido> atualizarLanchePedido(
	    @PathVariable(value = "id") Long idLanchePedido, @RequestBody LanchePedido lancheNovo)
	    throws Exception {
		LanchePedido lanche = 
				lancheRepository.findById(idLanchePedido)
					.orElseThrow(() -> new Exception("LanchePedido não encontrado: " + idLanchePedido));
		lanche.setAdicionais(lancheNovo.getAdicionais());

		LanchePedido lancheAtualizado = lancheRepository.save(lanche);
	    return ResponseEntity.ok(lancheAtualizado);
	}

	@DeleteMapping("/lanchesPedido/{id}")
	public Map<String, Boolean> apagarLanchePedido(@PathVariable(value = "id") Long idLanchePedido) throws Exception {
		LanchePedido lanche = 
				lancheRepository.findById(idLanchePedido)
					.orElseThrow(() -> new Exception("LanchePedido não encontrado: " + idLanchePedido));
	    lancheRepository.delete(lanche);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}

	@GetMapping("/lanchesPedido/{id}/preco")
	public ResponseEntity<Double> calcularPreco(@PathVariable(value = "id") Long idLanchePedido) throws Exception {
		LanchePedido lanche = 
				lancheRepository.findById(idLanchePedido)
					.orElseThrow(() -> new Exception("LanchePedido não encontrado: " + idLanchePedido));
		//TODO criar uma exception de not found
		
		Double preco = pedidoService.calcularPreco(lanche);
		
		return ResponseEntity.ok().body(preco);
	}


}
