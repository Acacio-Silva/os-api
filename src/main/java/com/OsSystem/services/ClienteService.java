package com.OsSystem.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OsSystem.domain.Pessoa;
import com.OsSystem.domain.Cliente;
import com.OsSystem.dto.ClienteDTO;
import com.OsSystem.repositorys.PessoaRepository;
import com.OsSystem.repositorys.ClienteRepository;
import com.OsSystem.services.exception.DataIntegratyViolationException;
import com.OsSystem.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return tecnicoRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		if(findByCpf(objDTO) != null) {
			throw new DataIntegratyViolationException("Cpf já cadastrado na base de dados");
		}
		return tecnicoRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Cliente update(@Valid Integer id, ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if (findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj =  findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui ordens de serviços pendendtes");
		}
		tecnicoRepository.deleteById(id);
		
	}
	
	
	private Pessoa findByCpf(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}



}
