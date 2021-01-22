package com.github.paulovictorpvc.hearthstoneapi.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.github.paulovictorpvc.hearthstoneapi.dto.CartaDto;
import com.github.paulovictorpvc.hearthstoneapi.model.Carta;
import com.github.paulovictorpvc.hearthstoneapi.model.Classe;
import com.github.paulovictorpvc.hearthstoneapi.model.Tipo;
import com.github.paulovictorpvc.hearthstoneapi.repository.CartaRepository;
import com.github.paulovictorpvc.hearthstoneapi.util.HearthStoneUtil;

@Path("/carta")
public class CartaResource {
	
	@Autowired
	CartaRepository cartaRepository;
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obter(@PathParam("id") Integer id) {
		Optional<Carta> optional = cartaRepository.findById(id);

		if (optional.isPresent()) {
			return Response.ok(CartaDto.converter(optional.get())).build();
		}

		return Response.ok().entity(new String[0]).build();
	}
	
	@GET
	@Path("/nome/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterPorNome(@PathParam("nome") String nome) {
		List<Carta> cartas = cartaRepository.findByNomeContaining(nome);
		
		return Response.ok(CartaDto.converter(cartas)).build();
	}
	
	@GET
	@Path("/tipo/{tipo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterPorTipo(@PathParam("tipo") String tipo) {
		Tipo tipoEnum = Tipo.obterPorNome(tipo);
		
		List<Carta> cartas = cartaRepository.findByTipo(tipoEnum);
		
		return Response.ok(CartaDto.converter(cartas)).build();
	}
	
	@GET
	@Path("/classe/{classe}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterPorClasse(@PathParam("classe") String classe) {
		Classe classeEnum = Classe.obterPorNome(classe);
		
		List<Carta> cartas = cartaRepository.findByClasse(classeEnum);
		
		return Response.ok(CartaDto.converter(cartas)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(@Valid Carta carta) {
		try {
			cartaRepository.save(carta);
		} catch (ConstraintViolationException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(HearthStoneUtil.obterMensagemDeErro(e)).build();
		}
		
		return Response.ok("Carta cadastrada com sucesso!").build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Integer id) {
		Optional<Carta> optional = cartaRepository.findById(id);
		
		if (optional.isPresent()) {
			try {
				cartaRepository.delete(optional.get());
			} catch (DataIntegrityViolationException e) {
				return Response.status(Response.Status.BAD_REQUEST).entity("A carta não pode ser excluída por estar em um baralho!").build();
			}
			return Response.ok("Carta excluída com sucesso!").build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Carta não encontrada!").build();
		}
	}

}
