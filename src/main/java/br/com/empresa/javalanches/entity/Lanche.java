package br.com.empresa.javalanches.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa uma opção do cardápio
 *
 */
@Entity
@Table(name="lanche")
public class Lanche implements Serializable {

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_lanche")
	@SequenceGenerator(name="seq_lanche", sequenceName = "seq_lanche", initialValue=1, allocationSize=1)
    private Long id;

	@Column(name = "nome")
    private String nome;

	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ingrediente_lanche", joinColumns = @JoinColumn(name="id_lanche"), inverseJoinColumns = @JoinColumn(name="id_ingrediente"))  
    private List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	
	@Enumerated(EnumType.STRING)
	private Promocao promocao;
	
	public Lanche() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
}