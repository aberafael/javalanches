package br.com.empresa.javalanches.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.javalanches.entity.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
