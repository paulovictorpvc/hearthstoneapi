package com.github.paulovictorpvc.hearthstoneapi.model;

public enum Tipo {

	MAGIA,
	CRIATURA;
	
	public static Tipo obterPorNome(String nome) {
		try {
			return Tipo.valueOf(nome);
		} catch (Exception e) {
			return null;
		}
	}
	
}
