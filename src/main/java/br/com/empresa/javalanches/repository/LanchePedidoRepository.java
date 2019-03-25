package br.com.empresa.javalanches.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.javalanches.entity.LanchePedido;

public interface LanchePedidoRepository extends JpaRepository<LanchePedido, Long> {

}
