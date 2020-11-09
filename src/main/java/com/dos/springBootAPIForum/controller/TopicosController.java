package com.dos.springBootAPIForum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dos.springBootAPIForum.modelo.Curso;
import com.dos.springBootAPIForum.modelo.Topico;

@Controller
public class TopicosController {

	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> lista() {
		Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("spring", "Programação"));
		return Arrays.asList(topico, topico, topico);
	}
}
