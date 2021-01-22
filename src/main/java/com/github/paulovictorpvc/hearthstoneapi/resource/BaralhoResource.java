package com.github.paulovictorpvc.hearthstoneapi.resource;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.github.paulovictorpvc.hearthstoneapi.dto.BaralhoDto;
import com.github.paulovictorpvc.hearthstoneapi.dto.CartaDto;
import com.github.paulovictorpvc.hearthstoneapi.model.Baralho;
import com.github.paulovictorpvc.hearthstoneapi.model.Carta;
import com.github.paulovictorpvc.hearthstoneapi.model.Classe;
import com.github.paulovictorpvc.hearthstoneapi.repository.BaralhoRepository;
import com.github.paulovictorpvc.hearthstoneapi.repository.CartaRepository;
import com.github.paulovictorpvc.hearthstoneapi.util.HearthStoneUtil;

@Path("/baralho")
public class BaralhoResource {

	@Autowired
	BaralhoRepository baralhoRepository;

	@Autowired
	CartaRepository cartaRepository;
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obter(@PathParam("id") Integer id) {
		Optional<Baralho> optional = baralhoRepository.findById(id);
		
		if (optional.isPresent()) {
			return Response.ok(BaralhoDto.converter(optional.get())).build();
		}
		

		return Response.ok().entity(new String[0]).build();
	}
	
	@GET
	@Path("/nome/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterPorNome(@PathParam("nome") String nome) {
		List<Baralho> baralhos = baralhoRepository.findByNomeContaining(nome);
		
		return Response.ok(BaralhoDto.converter(baralhos)).build();
	}
	
	@GET
	@Path("/classe/{classe}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterPorClasse(@PathParam("classe") String classe) {
		Classe classeEnum = Classe.obterPorNome(classe);
		
		List<Baralho> baralhos = baralhoRepository.findByClasse(classeEnum);
		
		return Response.ok(BaralhoDto.converter(baralhos)).build();
	}
	
	@GET
	@Path("/{id}/cartas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obterCartas(@PathParam("id") Integer id) {
		
		List<Carta> cartas = cartaRepository.findByBaralhos_Id(id);
		
		return Response.ok(CartaDto.converter(cartas)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(@Valid Baralho baralho) {
		String mensagem = validarCondicoes(baralho);

		if (!mensagem.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
		}

		try {
			baralhoRepository.save(baralho);
		} catch (ConstraintViolationException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(HearthStoneUtil.obterMensagemDeErro(e)).build();
		} catch (DataIntegrityViolationException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Uma ou mais carta(s) informada(s) não foram encontradas!").build();
		}

		return Response.ok("Baralho cadastrado com sucesso!").build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Integer id) {
		Optional<Baralho> optional = baralhoRepository.findById(id);

		if (optional.isPresent()) {
			baralhoRepository.delete(optional.get());
			return Response.ok("Baralho excluído com sucesso!").build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Baralho não encontrado!").build();
		}
	}

	private String validarCondicoes(Baralho baralho) {
		if (baralho.getCartas() == null) {
			return "Informe pelo menos 1 carta!";
		}

		List<Integer> idsCartas = baralho.getCartas().stream().filter(c -> c.getId() != null).map(Carta::getId)
				.collect(Collectors.toList());

		if (idsCartas.size() > 30) {
			return "É permitido no máximo 30 cartas no baralho!";
		} else if (idsCartas.size() == 0) {
			return "Informe pelo menos 1 carta!";
		}

		List<Carta> cartas = cartaRepository.findAllById(idsCartas);

		boolean cartaNaoPermitida = cartas.stream()
				.anyMatch(c -> !(c.getClasse().equals(baralho.getClasse()) || c.getClasse().equals(Classe.QUALQUER)));

		if (cartaNaoPermitida) {
			return "Só é permitida a inclusão de cartas da mesma classe do baralho ou da classe 'QUALQUER'!";
		}

		HashSet<Integer> set = new HashSet<Integer>(idsCartas);
		for (Integer integer : set) {
			if (Collections.frequency(idsCartas, integer) > 2) {
				return "É permitido no máximo 2 cartas iguais no mesmo baralho!";
			}
		}

		return "";
	}

}
