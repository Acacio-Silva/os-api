package com.OsSystem.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OsSystem.domain.Pessoa;
import com.OsSystem.domain.Tecnico;
import com.OsSystem.dto.TecnicoDTO;
import com.OsSystem.repositorys.PessoaRepository;
import com.OsSystem.repositorys.TecnicoRepository;
import com.OsSystem.services.exception.DataIntegratyViolationException;
import com.OsSystem.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Não encontrado! id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCpf(objDTO) != null) {
			throw new DataIntegratyViolationException("Cpf já cadastrado na base de dados");
		}
		return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Tecnico update(@Valid Integer id, TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if (findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return tecnicoRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj =  findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui ordens de serviços pendendtes");
		}
		tecnicoRepository.deleteById(id);
		
	}
	
	
	private Pessoa findByCpf(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}



}
