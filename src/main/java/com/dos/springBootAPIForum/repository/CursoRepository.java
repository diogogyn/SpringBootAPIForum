package com.dos.springBootAPIForum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dos.springBootAPIForum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
