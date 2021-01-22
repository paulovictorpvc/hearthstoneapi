package com.github.paulovictorpvc.hearthstoneapi.model;

public enum Classe {

	MAGO,
	PALADINO,
	CACADOR,
	DRUIDA,
	QUALQUER;
	
	public static Classe obterPorNome(String nome) {
		try {
			return Classe.valueOf(nome);
		} catch (Exception e) {
			return null;
		}
	}

}
