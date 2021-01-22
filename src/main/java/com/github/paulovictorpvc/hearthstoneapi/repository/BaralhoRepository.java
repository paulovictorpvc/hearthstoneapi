package com.github.paulovictorpvc.hearthstoneapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.paulovictorpvc.hearthstoneapi.model.Baralho;
import com.github.paulovictorpvc.hearthstoneapi.model.Classe;

public interface BaralhoRepository extends JpaRepository<Baralho, Integer> {
	
	List<Baralho> findByNomeContaining(String texto);
	
	List<Baralho> findByClasse(Classe classe);

}
