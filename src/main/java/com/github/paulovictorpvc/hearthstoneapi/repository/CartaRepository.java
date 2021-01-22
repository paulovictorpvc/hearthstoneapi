package com.github.paulovictorpvc.hearthstoneapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.paulovictorpvc.hearthstoneapi.model.Carta;
import com.github.paulovictorpvc.hearthstoneapi.model.Classe;
import com.github.paulovictorpvc.hearthstoneapi.model.Tipo;

public interface CartaRepository extends JpaRepository<Carta, Integer> {
	
	Carta findByNome(String nome);
	
	List<Carta> findByNomeContaining(String texto);
	
	List<Carta> findByClasse(Classe classe);
	
	List<Carta> findByTipo(Tipo tipo);
	
	List<Carta> findByBaralhos_Id(Integer id);

}
