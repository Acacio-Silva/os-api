package com.OsSystem.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.OsSystem.domain.Tecnico;
import com.OsSystem.dto.TecnicoDTO;
import com.OsSystem.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResourse {

	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
	 Tecnico obj = tecnicoService.findById(id);
	 TecnicoDTO objDTO = new TecnicoDTO(obj);
	 return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
	List<Tecnico> list = tecnicoService.findAll();
	List<TecnicoDTO> listDTO = new ArrayList<>();
	for(Tecnico obj : list) {
		listDTO.add(new TecnicoDTO(obj));
	}
	return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico newObj = tecnicoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@Valid @PathVariable Integer id, @RequestBody TecnicoDTO objDTO){
		TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
