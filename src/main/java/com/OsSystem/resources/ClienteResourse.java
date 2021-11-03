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

import com.OsSystem.domain.Cliente;
import com.OsSystem.dto.ClienteDTO;
import com.OsSystem.services.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResourse {

	@Autowired
	private ClienteService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
	 Cliente obj = tecnicoService.findById(id);
	 ClienteDTO objDTO = new ClienteDTO(obj);
	 return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
	List<Cliente> list = tecnicoService.findAll();
	List<ClienteDTO> listDTO = new ArrayList<>();
	for(Cliente obj : list) {
		listDTO.add(new ClienteDTO(obj));
	}
	return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
		Cliente newObj = tecnicoService.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Integer id, @RequestBody ClienteDTO objDTO){
		ClienteDTO newObj = new ClienteDTO(tecnicoService.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
