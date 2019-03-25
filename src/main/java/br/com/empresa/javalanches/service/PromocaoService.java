package br.com.empresa.javalanches.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.javalanches.entity.Ingrediente;
import br.com.empresa.javalanches.entity.LanchePedido;
import br.com.empresa.javalanches.repository.IngredienteRepository;

@Service
public class PromocaoService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	//TODO extrair no properties
	private static final Long ID_ALFACE = 1L;
	private static final Long ID_BACON = 2L;
	private static final Long ID_HAMBURGUER = 3L;
	private static final Long ID_QUEIJO = 5L;
	
	/**
	 * Aplica os descontos referentes a promoção
	 * @param lanchePedido
	 * @return Double
	 */
	public Double aplicarDescontoPromocao(Double preco, LanchePedido lanchePedido) {
		
		Double precoFinal = preco;
		
		if (lanchePedido.getLanche().getPromocao() != null)
			switch(lanchePedido.getLanche().getPromocao()) {
				case LIGHT: precoFinal = aplicarDescontoLight(preco, lanchePedido.getIngredientes()); break;
				case MUITA_CARNE: precoFinal = aplicarDescontoMuitaCarne(preco, lanchePedido.getIngredientes()); break;
				case MUITO_QUEIJO: precoFinal = aplicarDescontoMuitoQueijo(preco, lanchePedido.getIngredientes()); break;
			}
		
		return precoFinal;
	}


	/**
	 * Aplica o desconto Light
	 * @param preco
	 * @param ingredientes
	 * @return
	 */
	private Double aplicarDescontoLight(Double preco, List<Ingrediente> ingredientes) {
		long quantidadeAlface = ingredientes.stream().filter(p -> p.getId().equals(ID_ALFACE)).count();
		long quantidadeBacon = ingredientes.stream().filter(p -> p.getId().equals(ID_BACON)).count();
		
		if (quantidadeAlface > 0 && quantidadeBacon == 0)
			return preco * 0.9;
		
		return preco;
	}

	/**
	 * Aplica o desconto Muita Carne
	 * @param preco
	 * @param ingredientes
	 * @return
	 */
	private Double aplicarDescontoMuitaCarne(Double preco, List<Ingrediente> ingredientes) {
		Ingrediente hamburguer = ingredienteRepository.findById(ID_HAMBURGUER).get();
		
		long quantidadeHamburguer = ingredientes.stream().filter(p -> p.getId().equals(ID_HAMBURGUER)).count();
		
		preco = preco - (quantidadeHamburguer / 3 * hamburguer.getPreco());
		
		return preco;
	}
	
	/**
	 * Aplica o desconto Muito Queijo
	 * @param preco
	 * @param ingredientes
	 * @return
	 */
	private Double aplicarDescontoMuitoQueijo(Double preco, List<Ingrediente> ingredientes) {
		Ingrediente queijo = ingredienteRepository.findById(ID_QUEIJO).get();
		
		long quantidadeQueijo = ingredientes.stream().filter(p -> p.getId().equals(ID_QUEIJO)).count();
		
		preco = preco - (quantidadeQueijo / 3 * queijo.getPreco());
		
		return preco;
	}
}
