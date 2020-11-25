package com.dos.springBootAPIForum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dos.springBootAPIForum.controller.dto.DetalhesDoTopicoDto;
import com.dos.springBootAPIForum.controller.dto.TopicoDto;
import com.dos.springBootAPIForum.controller.form.AtualizacaoTopicoForm;
import com.dos.springBootAPIForum.controller.form.TopicoForm;
import com.dos.springBootAPIForum.modelo.Topico;
import com.dos.springBootAPIForum.repository.CursoRepository;
import com.dos.springBootAPIForum.repository.TopicoRepository;

@RestController
@RequestMapping(value="/topicos")
public class TopicosController {
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int qtde, @RequestParam String ordenacao) {
		Pageable paginacao = PageRequest.of(pagina, qtde, Direction.ASC, ordenacao);
		
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos); 
		}else {
			Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));			
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);			
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();			
		}
		return ResponseEntity.notFound().build();
	}
}
