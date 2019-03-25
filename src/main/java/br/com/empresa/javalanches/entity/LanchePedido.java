package br.com.empresa.javalanches.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa o lanche que o cliente pediu, o item do cardápio + suas customizações
 */
@Entity
@Table(name="lanche_pedido")
public class LanchePedido implements Serializable {

	private static final long serialVersionUID = -8525365173097186347L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_lanche_pedido")
	@SequenceGenerator(name="seq_lanche_pedido", sequenceName = "seq_lanche_pedido", initialValue=1, allocationSize=1)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "id_lanche")
	private Lanche lanche;

	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ingrediente_lanche_pedido", joinColumns = @JoinColumn(name="id_lanche_pedido"), inverseJoinColumns = @JoinColumn(name="id_ingrediente"))  
    private List<Ingrediente> adicionais = new ArrayList<Ingrediente>();

	public LanchePedido() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public List<Ingrediente> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<Ingrediente> adicionais) {
		this.adicionais = adicionais;
	}

	public List<Ingrediente> getIngredientes() {
		List<Ingrediente> lista = new ArrayList<Ingrediente>();
		lista.addAll(this.getLanche().getIngredientes());
		lista.addAll(this.adicionais);
		return Collections.unmodifiableList(lista);
	}
}