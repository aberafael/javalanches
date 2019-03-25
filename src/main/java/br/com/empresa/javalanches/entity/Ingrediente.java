package br.com.empresa.javalanches.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ingrediente")
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = -263267650583369554L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_ingrediente")
	@SequenceGenerator(name="seq_ingrediente", sequenceName = "seq_ingrediente", initialValue=1, allocationSize=1)
    private Long id;

	@Column(name = "nome")
    private String nome;
	
	@Column(name = "preco")
    private Double preco;

	//TODO poderia usar lombok
	
	public Ingrediente() {
		
	}
	
	public Ingrediente(String nome, Double preco) {
		this.nome = nome;
		this.preco = preco;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
