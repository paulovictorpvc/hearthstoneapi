package com.github.paulovictorpvc.hearthstoneapi.dto;

import java.util.ArrayList;
import java.util.List;

import com.github.paulovictorpvc.hearthstoneapi.model.Baralho;
import com.github.paulovictorpvc.hearthstoneapi.model.Classe;

public class BaralhoDto {

	private Integer id;
	private String nome;
	private Classe classe;

	public BaralhoDto(Integer id, String nome, Classe classe) {
		super();
		this.id = id;
		this.nome = nome;
		this.classe = classe;
	}
	
	public static BaralhoDto converter(Baralho baralho) {
		return new BaralhoDto(baralho.getId(), baralho.getNome(), baralho.getClasse());
	}
	
	public static List<BaralhoDto> converter(List<Baralho> baralhos) {
		ArrayList<BaralhoDto> retorno = new ArrayList<BaralhoDto>();
		for (Baralho baralho : baralhos) {
			retorno.add(new BaralhoDto(baralho.getId(), baralho.getNome(), baralho.getClasse()));
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

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

}
