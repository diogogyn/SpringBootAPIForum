package com.dos.springBootAPIForum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dos.springBootAPIForum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	List<Topico> findByCurso_Nome(String nomeCurso);
}
