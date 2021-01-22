package com.github.paulovictorpvc.hearthstoneapi.dto;

import java.util.ArrayList;
import java.util.List;

import com.github.paulovictorpvc.hearthstoneapi.model.Carta;
import com.github.paulovictorpvc.hearthstoneapi.model.Classe;
import com.github.paulovictorpvc.hearthstoneapi.model.Tipo;

public class CartaDto {

	private Integer id;
	private String nome;
	private String descricao;
	private Integer ataque;
	private Integer defesa;
	private Tipo tipo;
	private Classe classe;

	public CartaDto(Integer id, String nome, String descricao, Integer ataque, Integer defesa, Tipo tipo,
			Classe classe) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ataque = ataque;
		this.defesa = defesa;
		this.tipo = tipo;
		this.classe = classe;
	}

	public static CartaDto converter(Carta carta) {
		return new CartaDto(carta.getId(), carta.getNome(), carta.getDescricao(), carta.getAtaque(), carta.getDefesa(),
				carta.getTipo(), carta.getClasse());
	}

	public static List<CartaDto> converter(List<Carta> cartas) {
		ArrayList<CartaDto> retorno = new ArrayList<CartaDto>();
		for (Carta carta : cartas) {
			retorno.add(new CartaDto(carta.getId(), carta.getNome(), carta.getDescricao(), carta.getAtaque(),
					carta.getDefesa(), carta.getTipo(), carta.getClasse()));
		}
		return retorno;
	}

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

}
