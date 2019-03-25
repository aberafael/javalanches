package br.com.empresa.javalanches.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.javalanches.entity.Ingrediente;
import br.com.empresa.javalanches.entity.LanchePedido;

@Service
public class PedidoService {

	@Autowired
	private PromocaoService promocaoService;

	/**
	 * Faz o cálculo do preço do lanche pedido, considerando a possibilidade dele pertencer a promoção
	 * @param lanchePedido
	 * @return Double
	 */
	public Double calcularPreco(LanchePedido lanchePedido) {
		
		Double preco = somarIngredientes(lanchePedido);
		
		preco = promocaoService.aplicarDescontoPromocao(preco, lanchePedido);
		
		return preco;
	}
	
	/**
	 * Calcula o preço do lanche fazendo a soma dos ingredientes + adicionais
	 * @return Double
	 */
	public Double somarIngredientes(LanchePedido lanchePedido) {
		return lanchePedido.getIngredientes()
				.stream()
				.mapToDouble(Ingrediente::getPreco) //soma de acordo com o preço de cada ingrediente
				.sum();
	}
	

	
}