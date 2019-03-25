package br.com.empresa.javalanches.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.javalanches.entity.Lanche;

public interface LancheRepository extends JpaRepository<Lanche, Long> {

}
