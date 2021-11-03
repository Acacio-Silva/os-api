package com.OsSystem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.OsSystem.dto.OsDTO;
import com.OsSystem.services.OsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OsResource {

	@Autowired
	private OsService osService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
		OsDTO obj = new OsDTO(osService.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<OsDTO>> findAll(){
		List<OsDTO> list = osService.findAll().stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(osService.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(osService.update(obj));
		return ResponseEntity.ok().body(obj);
	}

}
