package com.dos.springBootAPIForum.controller.form;

import com.dos.springBootAPIForum.modelo.Curso;
import com.dos.springBootAPIForum.modelo.Topico;
import com.dos.springBootAPIForum.repository.CursoRepository;
import com.dos.springBootAPIForum.repository.TopicoRepository;

public class TopicoForm {
	private String titulo;
	private String mensagem;
	private String nomeCurso;
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
	
	 
}
