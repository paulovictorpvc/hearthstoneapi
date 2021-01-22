package com.github.paulovictorpvc.hearthstoneapi.util;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.github.paulovictorpvc.hearthstoneapi.model.Carta;

public class HearthStoneUtil {
	
	private static Pattern padrao = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	public static boolean isNumeric(String texto) {
		return padrao.matcher(texto).matches();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Carta> concatenar(List<Carta>... listas) {
		List<Carta> concat = Stream.of(listas).flatMap(x -> x.stream()).collect(Collectors.toList());
		
		return concat.stream().distinct().collect(Collectors.toList());
	}
	
	public static String obterMensagemDeErro(ConstraintViolationException e) {
		ConstraintViolation<?> next = e.getConstraintViolations().iterator().next();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Campo '");
		sb.append(next.getPropertyPath());
		sb.append("' ");
		sb.append(next.getMessage());
		sb.append("!");
		
		return sb.toString();
	}

}
