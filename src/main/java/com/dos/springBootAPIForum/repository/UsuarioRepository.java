package com.dos.springBootAPIForum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dos.springBootAPIForum.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
}
