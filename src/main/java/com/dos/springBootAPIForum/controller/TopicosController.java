package com.dos.springBootAPIForum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dos.springBootAPIForum.controller.dto.TopicoDto;
import com.dos.springBootAPIForum.modelo.Curso;
import com.dos.springBootAPIForum.modelo.Topico;
import com.dos.springBootAPIForum.repository.TopicoRepository;

@RestController
public class TopicosController {
	@Autowired
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista(String nomeCurso) {
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		}else {
			List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}
}
