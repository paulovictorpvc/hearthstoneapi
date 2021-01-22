package com.github.paulovictorpvc.hearthstoneapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Carta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String nome;

	@NotNull
	private String descricao;

	@NotNull
	@Min(0)
	@Max(10)
	private Integer ataque;

	@NotNull
	private Integer defesa;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Classe classe;

	@ManyToMany(mappedBy = "cartas")
	private List<Baralho> baralhos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public List<Baralho> getBaralhos() {
		return baralhos;
	}

	public void setBaralhos(List<Baralho> baralhos) {
		this.baralhos = baralhos;
	}

}
